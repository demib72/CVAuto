/*
 * The MIT License (MIT)
 *
 * Copyright (c) waicool20
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.waicool20.cvauto.android

import com.waicool20.cvauto.core.Millis
import com.waicool20.cvauto.core.Pixels
import com.waicool20.cvauto.core.Region
import com.waicool20.cvauto.core.input.IInput
import com.waicool20.cvauto.core.input.ITouchInterface
import net.jpountz.lz4.LZ4FrameInputStream
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.InputStream
import java.net.SocketException
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import java.util.concurrent.locks.ReentrantLock
import java.util.zip.GZIPInputStream
import kotlin.concurrent.withLock
import kotlin.math.roundToInt

class AndroidRegion(
    x: Pixels,
    y: Pixels,
    width: Pixels,
    height: Pixels,
    device: AndroidDevice,
    screen: Int
) : Region<AndroidDevice, AndroidRegion>(x, y, width, height, device, screen) {

    data class Stats(var captureRequests: Long = 0, var cacheHits: Long = 0)

    companion object {
        private val executorLock = ReentrantLock()
        private var executor = Executors.newSingleThreadExecutor()

        private var _stats = Stats()

        val stats get() = _stats.copy()
    }

    override fun capture(): BufferedImage {
        _stats.captureRequests++
        val last = device.screens[screen]._lastScreenCapture
        if (last != null && System.currentTimeMillis() - last.first <= 66) {
            _stats.cacheHits++
            val bi = BufferedImage(
                last.second.colorModel,
                last.second.copyData(last.second.raster.createCompatibleWritableRaster()),
                last.second.colorModel.isAlphaPremultiplied,
                null
            )
            return if (isDeviceScreen()) bi else bi.getSubimage(x, y, width, height)
        }
        return executorLock.withLock {
            val future = executor.submit<BufferedImage> {
                val capture = when (device.captureMethod) {
                    AndroidDevice.CaptureMethod.SCREENCAP -> doNormalCapture()
                    AndroidDevice.CaptureMethod.SCRCPY -> doScrcpyCapture()
                }
                device.screens[screen]._lastScreenCapture = System.currentTimeMillis() to capture
                return@submit if (isDeviceScreen()) {
                    capture
                } else {
                    capture.getSubimage(x, y, width, height)
                }
            }
            try {
                future.get(10_000, TimeUnit.MILLISECONDS)
            } catch (e: TimeoutException) {
                // Seems to work better if we just retry instead of re-throwing
                executor.shutdownNow()
                executor = Executors.newSingleThreadExecutor()
                return capture()
            } catch (e: ExecutionException) {
                throw e.cause ?: e
            }
        }
    }

    override fun click(random: Boolean) {
        if (random) {
            val r = randomPoint()
            device.input.touchInterface.tap(0, r.x, r.y)
        } else {
            device.input.touchInterface.tap(0, centerX.roundToInt(), centerY.roundToInt())
        }
    }

    override fun type(text: String) {
        click()
        device.input.keyboard.type(text)
    }

    /**
     * Swipes from this region to the other region
     * For more complex operations please use the devices respective [IInput]
     *
     * @param other Other region to swipe to
     * @param duration Duration of the swipe
     * @param random If true the source and destination location will be random points in their respective regions
     */
    fun swipeTo(other: AndroidRegion, duration: Millis = 1000, random: Boolean = true) {
        val swipe = if (random) {
            val src = randomPoint()
            val dest = other.randomPoint()
            ITouchInterface.Swipe(0, src.x, src.y, dest.x, dest.y)
        } else {
            ITouchInterface.Swipe(
                0,
                centerX.roundToInt(), centerY.roundToInt(),
                other.centerX.roundToInt(), other.centerY.roundToInt()
            )
        }
        device.input.touchInterface.swipe(swipe, duration)
    }

    /**
     * Sends a pinch gesture, centered at this regions center
     *
     * @param r1 Starting radius of the pinch
     * @param r2 End radius of the pinch
     * @param angle Pinch angle
     * @param duration Duration of the pinch
     */
    fun pinch(r1: Int, r2: Int, angle: kotlin.Double, duration: Millis = 1000) {
        device.input.touchInterface.pinch(
            centerX.roundToInt(), centerY.roundToInt(),
            r1, r2, angle, duration
        )
    }

    override fun copy(
        x: Pixels,
        y: Pixels,
        width: Pixels,
        height: Pixels,
        device: AndroidDevice,
        screen: Int
    ): AndroidRegion {
        return AndroidRegion(x, y, width, height, device, screen)
    }

    private fun doNormalCapture(): BufferedImage {
        val throwables = mutableListOf<Throwable>()
        for (i in 0 until 3) {
            val process: Process
            val inputStream = when (device.compressionMode) {
                AndroidDevice.CompressionMode.NONE -> {
                    process = device.execute("screencap")
                    process.inputStream.buffered(1024 * 1024)
                }
                AndroidDevice.CompressionMode.GZIP -> {
                    process = device.execute("screencap | toybox gzip -1")
                    GZIPInputStream(process.inputStream).buffered(1024 * 1024)
                }
                AndroidDevice.CompressionMode.LZ4 -> {
                    // LZ4 mode runs slower if buffered, maybe it has internal buffer already
                    process = device.execute("screencap | /data/local/tmp/lz4 -c -1")
                    LZ4FrameInputStream(process.inputStream)
                }
            }
            try {
                val width = inputStream.read() or (inputStream.read() shl 8) or
                    (inputStream.read() shl 16) or (inputStream.read() shl 24)
                val height = inputStream.read() or (inputStream.read() shl 8) or
                    (inputStream.read() shl 16) or (inputStream.read() shl 24)
                if (width < 0 || height < 0) continue
                if (device.screens[screen].width != width) device.screens[screen].width = width
                if (device.screens[screen].height != height) device.screens[screen].height = height
                if (device.properties.androidVersion.split(".")[0].toInt() >= 8) {
                    // ADB screencap on android versions 8 and above have 8 extra bytes instead of 4
                    // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/pie-release/cmds/screencap/screencap.cpp#247
                    inputStream.skip(8)
                } else {
                    // https://android.googlesource.com/platform/frameworks/base/+/refs/heads/nougat-release/cmds/screencap/screencap.cpp#191
                    inputStream.skip(4)
                }
                val img = BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)
                val buffer = (img.raster.dataBuffer as DataBufferByte).data
                for (n in buffer.indices step 3) {
                    // Data comes in RGBA byte format, repack into BGR
                    buffer[n + 2] = inputStream.read().toByte()
                    buffer[n + 1] = inputStream.read().toByte()
                    buffer[n + 0] = inputStream.read().toByte()
                    inputStream.skip(1)
                }
                return img
            } catch (t: Throwable) {
                device.assertConnected()
                throwables.add(t)
            }
        }
        throw CaptureIOException(throwables.reduceOrNull { acc, _ -> Exception(acc) }
            ?: Exception("Unknown cause"))
    }

    private var scrcpyStream: InputStream? = null

    private fun doScrcpyCapture(): BufferedImage {
        if (device.scrcpy.isClosed || device.scrcpy.video.isClosed || !device.scrcpy.video.isConnected) {
            scrcpyStream = null
            device.resetScrcpy()
            return doScrcpyCapture()
        }
        val inputStream = try {
            scrcpyStream?.takeIf { it.available() >= 0 } ?: device.scrcpy.video.getInputStream()
                .buffered()
        } catch (e: SocketException) {
            scrcpyStream = null
            device.resetScrcpy()
            return doScrcpyCapture()
        }
        scrcpyStream = inputStream
        try {
            val img = BufferedImage(
                device.properties.displayWidth,
                device.properties.displayHeight,
                BufferedImage.TYPE_3BYTE_BGR
            )
            val buffer = (img.raster.dataBuffer as DataBufferByte).data
            device.scrcpy.video.getOutputStream().write(1)
            for (n in buffer.indices step 3) {
                // Data comes in RGBA byte format, repack into BGR
                buffer[n + 2] = inputStream.read().toByte()
                buffer[n + 1] = inputStream.read().toByte()
                buffer[n + 0] = inputStream.read().toByte()
                inputStream.skip(1)
            }
            return img
        } catch (e: SocketException) {
            device.resetScrcpy()
            return doScrcpyCapture()
        } catch (t: Throwable) {
            device.assertConnected()
            throw CaptureIOException(t)
        }
    }
}

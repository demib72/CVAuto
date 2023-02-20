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

package com.waicool20.cvauto.core.util


import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.Scalar
import org.opencv.imgproc.Imgproc
import java.awt.Color
import java.awt.image.BufferedImage
import kotlin.math.*

/**
 * Counts the given color in the image
 *
 * @param color Color to count
 * @return Number of times this color appeared
 */
fun BufferedImage.countColor(color: Color): Int {
    val mat = toMat()
    val scalar = Scalar(color.blue.toDouble(), color.green.toDouble(), color.red.toDouble())
    Core.inRange(mat, scalar, scalar, mat)
    return Core.countNonZero(mat)
}

/**
 * Compares colors using CIE2000 deltaE formula
 *
 * Rule of thumb:
 *   - 0: Same color
 *   - (0, 1.0]: Basically the same color
 *   - (1.0 to 3.0]: Maybe some slight differences, but still the same color
 *   - (3.0 to 5.0]: Noticeably different
 *   - 5.0+: Different colors
 * @return true if difference is within maxDelta
 */
fun Color.isSimilar(other: Color, maxDelta: Double = 3.0): Boolean {
    val thisLAB = intArrayOf(red, green, blue)
    val otherLAB = intArrayOf(other.red, other.green, other.blue)

    ColorSpaceUtils.rgbToLab(thisLAB)
    ColorSpaceUtils.rgbToLab(otherLAB)
    ColorSpaceUtils.scaleRange(255, 100, thisLAB)
    ColorSpaceUtils.scaleRange(255, 100, otherLAB)

    val deltaE = calculateDeltaE(
        thisLAB[0].toDouble(), thisLAB[1].toDouble(), thisLAB[2].toDouble(),
        otherLAB[0].toDouble(), otherLAB[1].toDouble(), otherLAB[2].toDouble()
    )
    return deltaE <= maxDelta
}

/**
 * See original java implementation at
 * [OpenImaj](https://github.com/openimaj/openimaj/blob/df945aea4d037dbfae89ebb475966d1062c1cb63/image/image-processing/src/main/java/org/openimaj/image/analysis/colour/CIEDE2000.java#L85)
 */
private fun calculateDeltaE(
    L1: Double, a1: Double, b1: Double,
    L2: Double, a2: Double, b2: Double
): Double {
    val Lmean = (L1 + L2) / 2.0
    val C1 = sqrt(a1 * a1 + b1 * b1)
    val C2 = sqrt(a2 * a2 + b2 * b2)
    val Cmean = (C1 + C2) / 2.0
    val G = (1 - sqrt(Cmean.pow(7.0) / (Cmean.pow(7.0) + 25.0.pow(7.0)))) / 2
    val a1prime = a1 * (1 + G)
    val a2prime = a2 * (1 + G)
    val C1prime = sqrt(a1prime * a1prime + b1 * b1)
    val C2prime = sqrt(a2prime * a2prime + b2 * b2)
    val Cmeanprime = (C1prime + C2prime) / 2
    val h1prime = atan2(b1, a1prime) + 2 * Math.PI * if (atan2(b1, a1prime) < 0) 1 else 0
    val h2prime = atan2(b2, a2prime) + 2 * Math.PI * if (atan2(b2, a2prime) < 0) 1 else 0
    val Hmeanprime = if (abs(h1prime - h2prime) > Math.PI) {
        (h1prime + h2prime + 2 * Math.PI) / 2
    } else {
        (h1prime + h2prime) / 2
    }
    val T = (1.0
        - 0.17 * cos(Hmeanprime - Math.PI / 6.0) + 0.24 * cos(2 * Hmeanprime)
        + 0.32 * cos(3 * Hmeanprime + Math.PI / 30)
        - 0.2 * cos(4 * Hmeanprime - 21 * Math.PI / 60))
    val deltahprime = when {
        abs(h1prime - h2prime) <= Math.PI -> h2prime - h1prime
        h2prime <= h1prime -> h2prime - h1prime + 2 * Math.PI
        else -> h2prime - h1prime - 2 * Math.PI
    }
    val deltaLprime = L2 - L1
    val deltaCprime = C2prime - C1prime
    val deltaHprime = 2.0 * sqrt(C1prime * C2prime) * sin(deltahprime / 2.0)
    val SL = 1.0 + 0.015 * (Lmean - 50) * (Lmean - 50) / sqrt(20 + (Lmean - 50) * (Lmean - 50))
    val SC = 1.0 + 0.045 * Cmeanprime
    val SH = 1.0 + 0.015 * Cmeanprime * T
    val deltaTheta =
        30 * Math.PI / 180 * exp(-((180 / Math.PI * Hmeanprime - 275) / 25) * ((180 / Math.PI * Hmeanprime - 275) / 25))
    val RC = 2 * sqrt(Cmeanprime.pow(7.0) / (Cmeanprime.pow(7.0) + 25.0.pow(7.0)))
    val RT = -RC * sin(2 * deltaTheta)
    val KL = 1.0
    val KC = 1.0
    val KH = 1.0
    return sqrt(
        deltaLprime / (KL * SL) * (deltaLprime / (KL * SL)) +
            deltaCprime / (KC * SC) * (deltaCprime / (KC * SC)) +
            deltaHprime / (KH * SH) * (deltaHprime / (KH * SH)) +
            RT * (deltaCprime / (KC * SC)) * (deltaHprime / (KH * SH))
    )
}

object ColorSpaceUtils {
    fun rgbToHsv(color: IntArray) {
        convert(color, Imgproc.COLOR_RGB2HSV)
    }

    fun rgbToLab(color: IntArray) {
        convert(color, Imgproc.COLOR_RGB2Lab)
    }

    fun scaleRange(from: Int, to: Int, color: IntArray) {
        for (i in color.indices) {
            color[i] = (color[i] * (to / from.toDouble())).roundToInt()
        }
    }

    private fun convert(color: IntArray, mode: Int) {
        val mat = Mat(1, 1, CvType.CV_8UC3).apply {
            put(0, 0, byteArrayOf(color[0].toByte(), color[1].toByte(), color[2].toByte()))
        }
        Imgproc.cvtColor(mat, mat, mode)
        val out = mat[0, 0]
        color[0] = out[0].toInt()
        color[1] = out[1].toInt()
        color[2] = out[2].toInt()
    }
}

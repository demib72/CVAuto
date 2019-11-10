package com.waicool20.cvauto.android.input

import com.waicool20.cvauto.android.AndroidDevice
import com.waicool20.cvauto.android.readLines
import com.waicool20.cvauto.android.readText
import com.waicool20.cvauto.core.input.ITouchInterface
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class AndroidTouchInterface private constructor(
    private val device: AndroidDevice,
    private val devFile: DeviceFile,
    private val touchSpecs: Map<InputEvent, Specs>
) : ITouchInterface {
    /**
     * Specification of the acceptable touch values, see "ABS_MT_XXXX" in [InputEvent]
     *
     * @param minValue Minimum value
     * @param maxValue Maximum value
     * @param fuzz
     * @param flat
     * @param resolution
     */
    private data class Specs(
        val minValue: Int,
        val maxValue: Int,
        val fuzz: Int,
        val flat: Int,
        val resolution: Int
    ) {
        companion object {
            private val REGEX =
                Regex(".*?(\\w{4})\\s+:\\s+value \\d+, min (\\d+), max (\\d+), fuzz (\\d+), flat (\\d+), resolution (\\d+).*?")

            fun parse(spec: String): Pair<InputEvent, Specs>? {
                val (event, min, max, fuzz, flat, res) =
                    REGEX.matchEntire(spec)?.destructured ?: return null
                val iet = InputEvent[event.toLong(16)] ?: return null
                return iet to Specs(min.toInt(), max.toInt(), fuzz.toInt(), flat.toInt(), res.toInt())
            }
        }
    }

    private val _touches: List<ITouchInterface.Touch>

    companion object {
        private val rng = Random()
        fun getForDevice(device: AndroidDevice): AndroidTouchInterface? {
            val inputInfo = device.execute("getevent -p")
                .readText()
                .split("add device")
                .find { it.contains("ABS") }
                ?.lines() ?: error("No input devices found for device ${device.serial}")
            val devFile = DeviceFile(inputInfo[0].takeLastWhile { it != ' ' })
            val touchSpecs = inputInfo.drop(2)
                .mapNotNull(Specs.Companion::parse).toMap()
            return AndroidTouchInterface(device, devFile, touchSpecs)
        }
    }

    override val touches get() = Collections.unmodifiableList(_touches)

    init {
        // Determine max amount of touch slots the device can handle
        val maxSlots = touchSpecs[InputEvent.ABS_MT_SLOT]?.maxValue ?: 1
        _touches = MutableList(maxSlots) { ITouchInterface.Touch(it) }

        // Keep track of the x y coordinates by monitoring the input event bus
        thread(isDaemon = true) {
            device.execute("getevent $devFile").readLines().forEach {
                val (_, sCode, sValue) = it.trim().split(Regex("\\s+"))
                val eventCode = sCode.toLong(16)
                val value = sValue.toLong(16)
                when (eventCode) {
                    InputEvent.ABS_MT_POSITION_X.code -> {
                        _touches[0].cursorX = valueToCoord(value, InputEvent.ABS_MT_POSITION_X)
                    }
                    InputEvent.ABS_MT_POSITION_Y.code -> {
                        _touches[0].cursorY = valueToCoord(value, InputEvent.ABS_MT_POSITION_Y)
                    }
                }
            }
        }
    }

    override fun touchUp(slot: Int) = synchronized(this) {
        if (_touches[slot].isTouching) {
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_SLOT, slot.toLong())
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_PRESSURE, 0)
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_TRACKING_ID, 0xfffffffff)
            sendEvent(EventType.EV_KEY, InputEvent.BTN_TOOL_FINGER, InputEvent.KEY_UP.code)
            _touches[slot].isTouching = false
        }
    }

    override fun touchDown(slot: Int) = synchronized(this) {
        if (!_touches[slot].isTouching) {
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_SLOT, slot.toLong())
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_TRACKING_ID, slot.toLong())
            sendEvent(EventType.EV_KEY, InputEvent.BTN_TOOL_FINGER, InputEvent.KEY_DOWN.code)
            sendCoords(_touches[slot].cursorX, _touches[slot].cursorY)
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_TOUCH_MAJOR, 150L + rng.nextInt(50))
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_TOUCH_MINOR, 100L + rng.nextInt(50))
            sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_PRESSURE, 100L + rng.nextInt(100))
            _touches[slot].isTouching = true
        }
    }

    override fun touchMove(slot: Int, x: Int, y: Int) = synchronized(this) {
        val xChanged = _touches[slot].cursorX != x
        val yChanged = _touches[slot].cursorY != y
        _touches[slot].cursorX = if (xChanged) x else _touches[slot].cursorX
        _touches[slot].cursorY = if (yChanged) y else _touches[slot].cursorY
        if (_touches[slot].isTouching) {
            if (xChanged || yChanged) {
                sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_SLOT, slot.toLong())
                sendCoords(x, y)
            }
        }
    }

    override fun touchSync() = synchronized(this) {
        sendEvent(EventType.EV_SYN, InputEvent.SYN_REPORT, 0)
    }

    private fun sendCoords(x: Int, y: Int) {
        var xCoord = x
        var yCoord = y
        if (device.properties.displayWidth > device.properties.displayHeight) {
            when (device.orientation) {
                0, 2 -> {
                    xCoord = x
                    yCoord = y
                }
                1, 3 -> {
                    xCoord = device.properties.displayWidth - x
                    yCoord = device.properties.displayHeight - y
                }
            }
        } else {
            when (device.orientation) {
                0, 2 -> {
                    xCoord = y
                    yCoord = device.properties.displayHeight - x
                }
                1, 3 -> {
                    xCoord = device.properties.displayWidth - y
                    yCoord = x
                }
            }
        }
        sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_POSITION_X, coordToValue(xCoord, InputEvent.ABS_MT_POSITION_X))
        sendEvent(EventType.EV_ABS, InputEvent.ABS_MT_POSITION_Y, coordToValue(yCoord, InputEvent.ABS_MT_POSITION_Y))
    }

    private fun sendEvent(type: EventType, code: InputEvent, value: Long) {
        device.execute("sendevent $devFile ${type.code} ${code.code} $value").readText()
    }

    private fun valueToCoord(value: Long, event: InputEvent): Int {
        return when (event) {
            InputEvent.ABS_MT_POSITION_X -> {
                val max = touchSpecs[event]?.maxValue ?: 1
                ((value / max.toDouble()) * device.properties.displayWidth).roundToInt()
            }
            InputEvent.ABS_MT_POSITION_Y -> {
                val max = touchSpecs[event]?.maxValue ?: 1
                ((value / max.toDouble()) * device.properties.displayHeight).roundToInt()
            }
            else -> error("Unsupported event")
        }
    }

    private fun coordToValue(coord: Int, event: InputEvent): Long {
        return when (event) {
            InputEvent.ABS_MT_POSITION_X -> {
                val max = touchSpecs[event]?.maxValue ?: 1
                ((coord / device.properties.displayWidth.toDouble()) * max).roundToInt()
            }
            InputEvent.ABS_MT_POSITION_Y -> {
                val max = touchSpecs[event]?.maxValue ?: 1
                ((coord / device.properties.displayHeight.toDouble()) * max).roundToInt()
            }
            else -> error("Unsupported event")
        }.toLong()
    }
}
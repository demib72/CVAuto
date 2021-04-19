package com.waicool20.cvauto.android.input

import com.waicool20.cvauto.android.AndroidDevice
import com.waicool20.cvauto.core.input.CharactersPerSecond
import com.waicool20.cvauto.core.input.IKeyboard
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToLong
import kotlin.random.Random

class AndroidKeyboard private constructor(
    private val device: AndroidDevice
) : IKeyboard {
    data class AndroidKeyboardSettings(
        override var defaultTypingSpeed: CharactersPerSecond = 7,
        override var typingSpeedVariance: Double = 0.25
    ) : IKeyboard.Settings

    companion object {
        internal fun getForDevice(device: AndroidDevice): AndroidKeyboard {
            return AndroidKeyboard(device)
        }
    }

    private val _heldKeys = mutableListOf<String>()
    private val writeBuffer = ByteArray(14)

    override val settings: AndroidKeyboardSettings = AndroidKeyboardSettings()

    override val heldKeys: List<String> get() = Collections.unmodifiableList(_heldKeys)

    override fun keyUp(keyName: String): Unit = synchronized(this) {
        if (!heldKeys.contains(keyName)) return@synchronized
        val key = getKey(keyName)
        sendKeyEvent(key, InputEvent.KEY_ACTION_UP)
        _heldKeys.remove(keyName)
    }

    override fun keyDown(keyName: String): Unit = synchronized(this) {
        if (heldKeys.contains(keyName)) return@synchronized
        val key = getKey(keyName)
        sendKeyEvent(key, InputEvent.KEY_ACTION_DOWN)
        _heldKeys.add(keyName)
    }

    override fun checkSupport(keyName: String): Boolean {
        return getKey(keyName) != Key.KEY_UNKNOWN
    }

    override fun type(string: String, speed: CharactersPerSecond) {
        string.forEach { c ->
            if (Key.requiresShift(c)) keyDown("SHIFT")
            keyDown("$c")
            keyUp("$c")
            if (Key.requiresShift(c)) keyUp("SHIFT")
            TimeUnit.MILLISECONDS.sleep(
                (1000.0 / speed * (1 + Random.nextDouble(
                    -settings.typingSpeedVariance,
                    settings.typingSpeedVariance
                ))).roundToLong()
            )
        }
    }

    private fun getKey(keyName: String): Key {
        return when (keyName) {
            "CTRL" -> Key.KEY_CTRL_LEFT
            "ALT" -> Key.KEY_ALT_LEFT
            "SHIFT" -> Key.KEY_SHIFT_LEFT
            "WIN", "META" -> Key.KEY_META_LEFT
            else -> Key.findByName(keyName)
        }
    }

    private fun sendKeyEvent(key: Key, event: InputEvent) {
        var meta = 0
        if (_heldKeys.contains("CTRL")) meta = meta or Key.MASK_META_CTRL_ON.code.toInt()
        if (_heldKeys.contains("ALT")) meta = meta or Key.MASK_META_ALT_ON.code.toInt()
        if (_heldKeys.contains("SHIFT")) meta = meta or Key.MASK_META_SHIFT_ON.code.toInt()
        if (_heldKeys.contains("WIN") || _heldKeys.contains("META")) {
            meta = meta or Key.MASK_META_META_ON.code.toInt()
        }

        ByteBuffer.wrap(writeBuffer)
            .order(ByteOrder.BIG_ENDIAN)
            .put(ControlMessage.TYPE_INJECT_KEYCODE.toByte())
            .put(event.code.toByte())
            .putInt(key.code.toInt())
            .putInt(0) // Repeat
            .putInt(meta) // Meta flags

        try {
            device.scrcpy.control.getOutputStream().apply {
                write(writeBuffer)
                flush()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            device.resetScrcpy()
            sendKeyEvent(key, event)
        }
    }
}
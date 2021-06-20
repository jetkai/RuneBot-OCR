package runebot.ocr.misc

import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyEvent.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

class WinHook {

    companion object {

        fun init() { hook() }

        private fun hook() = try {
            LogManager.getLogManager().reset()
            val logger = Logger.getLogger(GlobalScreen::class.java.getPackage().name)
            logger.level = Level.OFF
            GlobalScreen.registerNativeHook()
        } catch (ignored: NativeHookException) { }

        fun pressKeyAfter(key : Int, delay : Int) {
            OCRHandler.getOCRHandler().schedule(object : Event(delay) {
                override fun run() {
                    pressKey(key)
                    this.stop()
                }
            })
        }

        fun pressKeyAfter(key : Int, delay : Int, amount : Int) {
            OCRHandler.getOCRHandler().schedule(object : Event(delay) {
                override fun run() {
                    repeat((0 until amount).count()) { pressKey(key) }
                    this.stop()
                }
            })
        }

        fun pressKey(key : Int) {
            GlobalScreen.postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, key, key.toChar(), KEY_LOCATION_UNKNOWN))
        }

        private fun releaseKey(key : Int) {
            GlobalScreen.postNativeEvent(NativeKeyEvent(NATIVE_KEY_RELEASED, 0, 81, key, key.toChar(), KEY_LOCATION_UNKNOWN))
        }

        fun copy(data : String) {
            val stringSelection = StringSelection(data)
            return Toolkit.getDefaultToolkit().systemClipboard.setContents(stringSelection, stringSelection)
        }

        fun paste() {
            val keyArray = arrayOf(VC_CONTROL, VC_V)
            keyArray.forEach { key -> pressKey(key) }
            releaseKey(VC_CONTROL)
        }
    }

}
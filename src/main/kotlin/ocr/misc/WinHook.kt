package ocr.misc

import ocr.OCRHandler
import ocr.event.Event
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

        fun pressKey(keyCode : Int) {
            GlobalScreen.postNativeEvent(
                NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81,
                keyCode, keyCode.toChar(), KEY_LOCATION_UNKNOWN))
        }

        fun releaseKey(keyCode : Int) {
            GlobalScreen.postNativeEvent(
                NativeKeyEvent(NATIVE_KEY_RELEASED, 0, 81,
                    keyCode, keyCode.toChar(), KEY_LOCATION_UNKNOWN))
        }

        fun copy(data : String) {
            val stringSelection = StringSelection(data)
            return Toolkit.getDefaultToolkit().systemClipboard.setContents(stringSelection, stringSelection)
        }

        fun paste() {
            val keyArray = arrayOf(VC_CONTROL, VC_V);
            keyArray.forEach { key ->
                pressKey(key)
            }
            releaseKey(VC_CONTROL);
        }

        fun enterAfter(delay : Int) {
            OCRHandler.getOCRHandler().schedule(object : Event(delay) {
                override fun run() {
                    pressKey(VC_ENTER)
                    this.stop()
                }
            })
        }

        fun enterAfter(delay : Int, amount : Int) {
            OCRHandler.getOCRHandler().schedule(object : Event(delay) {
                override fun run() {
                    repeat((0 until amount).count()) {
                        pressKey(VC_ENTER)
                    }
                    this.stop()
                }
            })
        }
    }

}
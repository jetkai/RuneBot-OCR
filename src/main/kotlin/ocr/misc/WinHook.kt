package ocr.misc

import org.jnativehook.GlobalScreen
import org.jnativehook.NativeHookException
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
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
    }

}
package runebot.ocr.event.impl

import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent
import kotlin.random.Random

class CookEvent : Event(Random.nextInt(1920, 2460)) { //1920s = 32 minutes

    override fun run() {
        this.delay = Random.nextInt(1920, 2460)
        if(!TriviaEvent.inProgress)
            type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to cook ${Constants.fish}")
        //Copy below string to clipboard
        WinHook.copy("/cook item:${Constants.fish}")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
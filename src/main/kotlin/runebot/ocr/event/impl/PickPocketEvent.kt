package runebot.ocr.event.impl

import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent
import kotlin.random.Random

class PickPocketEvent : Event(Random.nextInt(1920, 2460)) { //32 minutes

    override fun run() {
        this.delay = Random.nextInt(1920, 2460)
        if(!TriviaEvent.inProgress)
            type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to pick the pocket of ${Constants.npc}")
        //Copy below string to clipboard
        WinHook.copy("/pickpocket runebot.npc:${Constants.npc}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB} //TODO POSSIBLE BUG, COMMENTED OUT
        /*WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)*/
        //2 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
    }
}
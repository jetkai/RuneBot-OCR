package runebot.ocr.event.impl

import org.jnativehook.keyboard.NativeKeyEvent
import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook

class PickPocketEvent : Event(Constants.waitTime()) { //32 minutes

    override fun run() {
        this.delay = Constants.waitTime()
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
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
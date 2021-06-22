package runebot.ocr.event.impl

import org.jnativehook.keyboard.NativeKeyEvent
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook

class ThievingStallEvent : Event(35) { //35 seconds

    override fun run() {
        if(!TriviaEvent.inProgress)
            type()
    }

    //TODO Complete Random Event (Trivia Questions): "@jet kai, random event, answer the following question... Name a guthix shield."
    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to thieve the stall")
        //Copy below string to clipboard
        WinHook.copy("/ts")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
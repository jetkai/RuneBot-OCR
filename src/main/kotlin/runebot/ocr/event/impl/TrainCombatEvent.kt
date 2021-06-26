package runebot.ocr.event.impl

import org.jnativehook.keyboard.NativeKeyEvent
import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook

class TrainCombatEvent : Event(Constants.longWaitTime()) { //1920s = 32 minutes, 7400s = 2 hours and a few minutes

    override fun run() {
        this.delay = Constants.longWaitTime()
        if(!TriviaEvent.inProgress)
            type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to train on ${Constants.monster} with combat-style ${Constants.combatType}")
        //Copy below string to clipboard
        WinHook.copy("/kill combat-style:${Constants.combatType} monster:${Constants.monster} time:${Constants.time}")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
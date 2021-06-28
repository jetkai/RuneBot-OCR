package runebot.ocr.event.impl

import org.jnativehook.keyboard.NativeKeyEvent
import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook

class FishEvent : Event(Constants.longWaitTime()) { //1920s = 32 minutes

    override fun run() {
        this.delay = Constants.longWaitTime()
        when { !TriviaEvent.inProgress -> type() }
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to fish ${Constants.fish}")
        //Copy below string to clipboard
        WinHook.copy("/fish fish:${Constants.fish} time:${Constants.time}")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
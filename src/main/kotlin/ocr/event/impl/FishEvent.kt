package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent

class FishEvent : Event(1920) { //1920s = 32 minutes

    override fun run() {
        if(TriviaEvent.inProgress)
            Thread.sleep(120000) //Sleep for 2 minutes
        type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to fish ${Constants.fish}")
        //Copy below string to clipboard
        WinHook.copy("/fish fish:${Constants.fish}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)
        //2 seconds later, press {ENTER}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2)
    }
}
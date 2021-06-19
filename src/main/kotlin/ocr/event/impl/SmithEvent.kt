package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent

class SmithEvent : Event(1920) { //1920s = 32 minutes

    override fun run() {
        if(TriviaEvent.inProgress)
            Thread.sleep(120000) //Sleep for 2 minutes
        type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to smith item ${Constants.smithItem}")
        //Copy below string to clipboard
        WinHook.copy("/smith item:${Constants.smithItem}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB} //TODO POSSIBLE BUG, COMMENTED OUT
        /*WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)*/
        //2 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
    }
}
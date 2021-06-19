package ocr.event.impl

import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent

class ThievingEvent : Event(35) { //35 seconds

    override fun run() {
        if(TriviaEvent.inProgress)
            Thread.sleep(120000) //Sleep for 2 minutes
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
        //1 second later, press {TAB} //TODO POSSIBLE BUG, COMMENTED OUT
        /*WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)*/
        //2 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
    }
}
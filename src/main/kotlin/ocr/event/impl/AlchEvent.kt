package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent

class AlchEvent : Event(2120) { //2120s = ~35 minutes, 7400s = 2 hours and a few minutes

    override fun run() {
        if(TriviaEvent.inProgress)
            Thread.sleep(120000) //Sleep for 2 minutes
        type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to alch ${Constants.alchAmount} of ${Constants.alchItem}")
        //Copy below string to clipboard
        WinHook.copy("/alch item:${Constants.alchItem} amount:${Constants.alchAmount}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)
        //2 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
    }
}
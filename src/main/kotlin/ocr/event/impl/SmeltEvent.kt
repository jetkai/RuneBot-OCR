package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent
import kotlin.random.Random

class SmeltEvent : Event(Random.nextInt(1920, 2460)) { //1920s = 32 minutes

    override fun run() {
        this.delay = Random.nextInt(1920, 2460)
        if(!TriviaEvent.inProgress)
            type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to smelt bar ${Constants.bar}")
        //Copy below string to clipboard
        WinHook.copy("/smelt bar:${Constants.bar}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB} //TODO POSSIBLE BUG, COMMENTED OUT
        /*WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)*/
        //2 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
    }
}
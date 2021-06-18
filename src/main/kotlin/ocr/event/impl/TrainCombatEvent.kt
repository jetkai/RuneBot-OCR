package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class TrainCombatEvent : Event(1920) { //1920s = 32 minutes

    override fun run() {
        if(TriviaEvent.inProgress)
            Thread.sleep(120000) //Sleep for 2 minutes
        type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to train on ${Constants.monster} with combat-style ${Constants.combatType}")
        //Copy answer to clipboard
        WinHook.copy("/kill combat-style:${Constants.combatType} monster:${Constants.monster}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {TAB}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_TAB, 1)
        //2 seconds later, press {ENTER}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2)
    }
}
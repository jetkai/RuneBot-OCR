package ocr.event.impl

import ocr.Constants
import ocr.event.Event
import ocr.misc.WinHook
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
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        println("[TrainEvent] - Attempting to train on ${Constants.monster} with combat-style ${Constants.combatType} @ ${current.format(formatter)}")
        //Copy answer to clipboard
        WinHook.copy("/kill combat-style:${Constants.combatType} monster:${Constants.monster}")
        //Paste the copied string
        WinHook.paste()
        //1 second later, press {ENTER}
        WinHook.enterAfter(1)
    }
}
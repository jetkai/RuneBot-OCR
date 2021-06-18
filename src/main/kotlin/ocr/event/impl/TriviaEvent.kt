package ocr.event.impl

import content.trivia.Trivia
import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.API
import ocr.misc.CaptureScreen
import ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class TriviaEvent : Event(22000) { //22,000 (seconds) = 6 Hours and a lil bit

    companion object {
        var inProgress : Boolean = false
    }

    override fun run() {
        inProgress = true
        type()
    }

    private fun type() {
        //Print last time executed in log
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formattedTime = current.format(formatter)

        println("[TriviaEvent] - Attempting to answer a question @ $formattedTime")

        //Enter the string "/daily"
        val keyArray = arrayOf(VC_SLASH, VC_D, VC_A, VC_I, VC_L, VC_Y);
        keyArray.forEach { key ->
            WinHook.pressKey(key)
        }
        //1 second later, press {ENTER} x2
        WinHook.enterAfter(1, 2)
        //3 seconds later, capture screenshot (in-case bot is delayed)
        OCRHandler.getOCRHandler().schedule(object : Event(3) {
            override fun run() {
                answer()
                this.stop()
            }
        })
    }

    private fun question() : String {
        val content = API.getOCRResults(CaptureScreen.getBase64Encoded())
        val searchString = "Diango asks " + Constants.discordName
        val maximumLength = 150 //150 to be safe
        return if (content.contains(searchString))
            content.substring(
                content.indexOf(searchString) + searchString.length + 4,
                content.indexOf(searchString) + searchString.length + maximumLength
            )
        else
            content
    }

    private fun answer() {
        val question = question()
        val answer = Trivia.getAnswer(question)
        //Copy answer to clipboard
        WinHook.copy(answer)
        //CTRL+V (Paste)
        WinHook.paste()
        //1 second later, press {ENTER}
        WinHook.enterAfter(1)
        println("Finished answering: \"$question\", it was \"$answer\".")
        inProgress = false
    }

}
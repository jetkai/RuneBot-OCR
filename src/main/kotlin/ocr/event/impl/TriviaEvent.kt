package ocr.event.impl

import ocr.Constants
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.API
import ocr.misc.CaptureScreen
import ocr.misc.WinHook
import ocr.trivia.Trivia
import org.jnativehook.keyboard.NativeKeyEvent.*
import kotlin.random.Random

class TriviaEvent : Event(Random.nextInt(220000, 240000)) { //22,000 (seconds) = 6 Hours and a lil bit

    companion object {
        var inProgress : Boolean = false
    }

    override fun run() {
        this.delay = Random.nextInt(220000, 240000);
        inProgress = true
        type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to answer a question")
        //Enter the string "/daily"
        val keyArray = arrayOf(VC_SLASH, VC_D, VC_A, VC_I, VC_L, VC_Y)
        keyArray.forEach { key -> WinHook.pressKey(key) }
        //1 second later, press {ENTER} x2
        WinHook.pressKeyAfter(VC_ENTER, 1, 2)
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
        //Takes a screenshot of the screen, uploads to OCR API and extracts the question
        val question = question()
        //Matches best possible answer to the question that is offered
        val answer = Trivia.getAnswer(question)
        //Copy answer to clipboard
        WinHook.copy(answer)
        //Paste the answer from the clipboard
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(VC_SPACE, 2)
        //3 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(VC_ENTER, 3, 2)
        //Event Complete
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Finished answering: \"$question\", it was \"$answer\".")
        inProgress = false
    }

}
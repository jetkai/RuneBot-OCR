package ocr.event.impl

import content.trivia.Trivia
import ocr.OCR
import ocr.OCRHandler
import ocr.event.Event
import ocr.misc.API
import ocr.misc.CaptureScreen
import org.jnativehook.GlobalScreen.postNativeEvent
import org.jnativehook.keyboard.NativeKeyEvent
import org.jnativehook.keyboard.NativeKeyEvent.*
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class TriviaEvent : Event(22000) { //22,000 (seconds) = 6 Hours and a lil bit

    override fun run() {
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
            postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, key, key.toChar(), KEY_LOCATION_UNKNOWN))
        }
        //1 second later, press {ENTER} x2
        OCRHandler.getOCRHandler().schedule(object : Event(1) {
            override fun run() {
                postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, VC_ENTER, VC_ENTER.toChar(), KEY_LOCATION_UNKNOWN))
                postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, VC_ENTER, VC_ENTER.toChar(), KEY_LOCATION_UNKNOWN))
                this.stop()
            }
        })
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
        val searchString = "Diango asks " + OCR.discordName
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
        val selection = StringSelection(answer)
        val keyArray = arrayOf(VC_CONTROL, VC_V);
        //Copy answer to clipboard
        Toolkit.getDefaultToolkit().systemClipboard.setContents(selection, selection)
        //CTRL+V (Paste)
        keyArray.forEach { key ->
            postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, key, key.toChar(), KEY_LOCATION_UNKNOWN))
        }
        postNativeEvent(NativeKeyEvent(NATIVE_KEY_RELEASED, 0, 81, VC_CONTROL, VC_CONTROL.toChar(), KEY_LOCATION_UNKNOWN));
        //1 second later, press {ENTER}
        OCRHandler.getOCRHandler().schedule(object : Event(1) {
            override fun run() {
                postNativeEvent(NativeKeyEvent(NATIVE_KEY_PRESSED, 0, 81, VC_ENTER, VC_ENTER.toChar(), KEY_LOCATION_UNKNOWN))
                this.stop()
            }
        })
        println("Finished answering: \"$question\", it was \"$answer\".")
    }

}
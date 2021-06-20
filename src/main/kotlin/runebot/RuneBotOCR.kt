package runebot

import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.misc.WinHook
import runebot.ocr.trivia.Trivia
import java.util.*

class RuneBotOCR {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            OCRHandler.getOCRHandler().log("OCR-INIT", "Starting up RuneBot OCR : Welcome " +
                    Constants.discordName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    } + ".")
            //Hooks into Windows, can listen & register key inputs
            println(">>Loading Keyboard & Mouse Hook<<")
            WinHook.init()
            //Loads Trivia questions from JSON
            println(">>Loading Trivia Questions<<")
            Trivia.init()
            //Initiates MainThread & Events
            println(">>Loading OCRHandler<<")
            OCRHandler.getOCRHandler().init()
            //Run Cleanup
            println(">>Finalizing<<")
            System.gc()
            System.runFinalization()
            //Completed
            OCRHandler.getOCRHandler().log("OCR-INIT", "Events loaded successfully, now running...")
        }

    }

}
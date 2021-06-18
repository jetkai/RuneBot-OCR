package ocr

import content.trivia.Trivia
import ocr.misc.WinHook
import java.util.*

class OCR {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting up RuneBot OCR : Welcome " +
                    Constants.discordName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    } + ".")
            println("Loading Keyboard & Mouse Hook")
            WinHook.init()
            println("Loading Trivia Questions")
            Trivia.init()
            println("Loading OCRHandler")
            OCRHandler.getOCRHandler().init();
            println("Cleaning up")
            System.gc()
            System.runFinalization()
            println("Running...")
        }
    }

}
package ocr

import content.trivia.Trivia
import ocr.misc.WinHook
import java.util.*

class OCR {

    companion object {

        const val discordName = "kai"

        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting up RuneBot OCR : Welcome " +
                    discordName.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                    } + ".")
            println("Loading Keyboard & Mouse Hook")
            WinHook.init()
            println("Loading Trivia Questions")
            Trivia.init()
            println("Loading OCRHandler")
            OCRHandler.getOCRHandler().init();
            System.gc()
            System.runFinalization()
        }
    }

}
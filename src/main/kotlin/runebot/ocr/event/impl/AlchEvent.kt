package runebot.ocr.event.impl

import runebot.ocr.Constants
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook
import org.jnativehook.keyboard.NativeKeyEvent
import kotlin.random.Random

class AlchEvent : Event(Random.nextInt(2115, 4215)) { //2120s = ~35 minutes, 7400s = 2 hours and a few minutes

    override fun run() {
        this.delay = Random.nextInt(2115, 4215);
        if(!TriviaEvent.inProgress)
            type()
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to alch ${Constants.alchAmount} of ${Constants.alchItem}")
        //Copy below string to clipboard
        WinHook.copy("/alch item:${Constants.alchItem} amount:${Constants.alchAmount}")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)

        OCRHandler.getOCRHandler().schedule(object : Event(8) {
            override fun run() {
                WinHook.copy("confirm")
                //Paste the copied string
                WinHook.paste()
                //4 seconds later, press {ENTER} x2
                WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 2, 2)
                this.stop()
            }
        })
    }
}
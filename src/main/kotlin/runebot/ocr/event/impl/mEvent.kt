package runebot.ocr.event.impl

import org.jnativehook.keyboard.NativeKeyEvent
import runebot.ocr.OCRHandler
import runebot.ocr.event.Event
import runebot.ocr.misc.WinHook
import kotlin.random.Random

class mEvent : Event(Random.nextInt(2115, 4215)) { //2120s = ~35 minutes, 7400s = 2 hours and a few minutes

    override fun run() {
        this.delay = Random.nextInt(2115, 4215)
        when { !TriviaEvent.inProgress -> type() }
    }

    private fun type() {
        //Print last time executed in log
        OCRHandler.getOCRHandler().log(this.javaClass.name, "Attempting to check current task")
        //Copy below string to clipboard
        WinHook.copy("/m")
        //Paste the copied string
        WinHook.paste()
        //2 second later, press {SPACE}
        WinHook.pressKeyAfter(NativeKeyEvent.VC_SPACE, 2)
        //4 seconds later, press {ENTER} x2
        WinHook.pressKeyAfter(NativeKeyEvent.VC_ENTER, 4, 2)
    }
}
package runebot.ocr.event.impl

import runebot.ocr.Constants
import runebot.ocr.event.Event

class SlayerEvent : Event(Constants.waitTime()) { //1 Second Delay - TODO
    override fun run() {
        println("Event Tick Test.")
    }
}
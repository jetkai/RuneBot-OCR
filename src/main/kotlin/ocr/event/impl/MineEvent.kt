package ocr.event.impl

import ocr.event.Event

class MineEvent : Event(1) { //1 Second Delay
    override fun run() {
        println("Event Tick Test.")
    }
}
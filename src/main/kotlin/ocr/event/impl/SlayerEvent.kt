package ocr.event.impl

import ocr.event.Event

class SlayerEvent : Event(1) { //1 Second Delay - TODO
    override fun run() {
        println("Event Tick Test.")
    }
}
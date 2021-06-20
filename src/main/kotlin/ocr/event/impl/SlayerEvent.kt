package ocr.event.impl

import ocr.event.Event
import kotlin.random.Random

class SlayerEvent : Event(Random.nextInt(1920, 2460)) { //1 Second Delay - TODO
    override fun run() {
        println("Event Tick Test.")
    }
}
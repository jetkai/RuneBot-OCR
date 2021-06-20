package ocr

import ocr.event.Event
import ocr.event.impl.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class OCRHandler {

    private val executor : OCRExecutorService = OCRExecutorService()

    companion object {
        private val INSTANCE : OCRHandler = OCRHandler()
        fun getOCRHandler() : OCRHandler {
            return INSTANCE
        }
    }

    fun init() { registerEvents() }

    private fun registerEvents() { //TODO - Improve this
        when { Constants.triviaEventEnabled -> schedule(TriviaEvent()) }
        when { Constants.trainCombatEventEnabled -> schedule(TrainCombatEvent()) }
        when { Constants.mineEventEnabled -> schedule(MineEvent()) }
        when { Constants.buryEventEnabled -> schedule(BuryEvent()) }
        when { Constants.chopEventEnabled -> schedule(ChopEvent()) }
        when { Constants.fishEventEnabled -> schedule(FishEvent()) }
        when { Constants.cookEventEnabled -> schedule(CookEvent()) }
        when { Constants.thievingEventEnabled -> schedule(ThievingStallEvent()) }
        when { Constants.thievingEventEnabled -> schedule(ThievingStallEvent()) }
        when { Constants.pickPocketEventEnabled -> schedule(PickPocketEvent()) }
        when { Constants.smeltEventEnabled -> schedule(SmeltEvent()) }
        when { Constants.smithEventEnabled -> schedule(SmithEvent()) }
        when { Constants.barrowsEventEnabled -> schedule(BarrowsEvent()) }
        when { Constants.alchEventEnabled -> schedule(AlchEvent()) }
        when { Constants.mEventEnabled -> schedule(mEvent()) }
    }

    fun schedule(event : Event) {
        executor.scheduleAtFixedRate(event)
    }

    fun log(className : String, eventText : String) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        println("[$className] - $eventText @ ${current.format(formatter)}")
    }

}
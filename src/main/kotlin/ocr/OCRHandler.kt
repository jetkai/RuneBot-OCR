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
        if(Constants.triviaEventEnabled)
            schedule(TriviaEvent())
        if(Constants.trainCombatEventEnabled)
            schedule(TrainCombatEvent())
        if(Constants.mineEventEnabled)
            schedule(MineEvent())
        if(Constants.buryEventEnabled)
            schedule(BuryEvent())
        if(Constants.chopEventEnabled)
            schedule(ChopEvent())
        if(Constants.fishEventEnabled)
            schedule(FishEvent())
        if(Constants.cookEventEnabled)
            schedule(CookEvent())
        if(Constants.thievingEventEnabled)
            schedule(ThievingStallEvent())
        if(Constants.thievingEventEnabled)
            schedule(ThievingStallEvent())
        if(Constants.pickPocketEventEnabled)
            schedule(PickPocketEvent())
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
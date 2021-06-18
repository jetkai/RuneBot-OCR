package ocr

import ocr.event.Event
import ocr.event.impl.TrainCombatEvent
import ocr.event.impl.TriviaEvent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class OCRHandler {

    private val executor: OCRExecutorService = OCRExecutorService()

    companion object {
        private val INSTANCE: OCRHandler = OCRHandler()
        fun getOCRHandler(): OCRHandler {
            return INSTANCE
        }
    }

    fun init() { registerEvents() }

    private fun registerEvents() {
        if(Constants.triviaEventEnabled)
            schedule(TriviaEvent())
        if(Constants.trainCombatEventEnabled)
            schedule(TrainCombatEvent())
    }

    fun schedule(event: Event) {
        executor.scheduleAtFixedRate(event)
    }

    fun log(className : String, eventText : String) {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        println("[$className] - $eventText @ ${current.format(formatter)}")
    }

}
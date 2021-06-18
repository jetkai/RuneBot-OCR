package ocr

import ocr.event.Event
import ocr.event.impl.TriviaEvent

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
        schedule(TriviaEvent())
    }

    fun schedule(event: Event) {
        executor.scheduleAtFixedRate(event)
    }

}
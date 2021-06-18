package ocr

import ocr.event.Event
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class OCRExecutorService {

    private val mainExecutor = Executors.newScheduledThreadPool(1, OCRThreadFactory("MainExecutor"))

    fun scheduleAtFixedRate(event: Event) {
        mainExecutor.scheduleAtFixedRate({
            if (event.isRunning) {
                try {
                    event.run()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }, event.delay.toLong(), event.delay.toLong(), TimeUnit.SECONDS)
    }

}
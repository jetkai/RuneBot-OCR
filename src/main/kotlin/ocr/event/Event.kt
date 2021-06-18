package ocr.event

abstract class Event(var delay: Int) {

    var isRunning = true

    fun stop() {
        isRunning = false
    }

    abstract fun run()
}
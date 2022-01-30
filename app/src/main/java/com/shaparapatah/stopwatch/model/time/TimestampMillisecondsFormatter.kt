package com.shaparapatah.stopwatch.model.time

class TimestampMillisecondsFormatter() {

    /** Тут мы просто делим значение в миллисекундах на 1000 или на 60 без остатка.
    Если время на секундомере меньше часа, то мы отображаем минуты, секунды и миллисекунды.
    Если больше — отображаем часы, минуты и секунды.  */

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatter = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatter = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatter = (minutes / 60).pad(2)
            "$hoursFormatter:$minutesFormatter:$secondsFormatter"
        } else {
            "$minutesFormatter:$secondsFormatter:$millisecondsFormatted"
        }
    }

    /** С помощью функции-расширения Long.pad, которую мы написали сами и благодаря
    использованию функции padStart мы просто отображаем нужное количество
    нулей для каждого параметра, обращаясь напрямую к типу Long. */

    private fun Long.pad(desiredLength: Int) =
        this.toString().padStart(desiredLength, '0')

    companion object {
        const val DEFAULT_TIME = "00:00:000"
    }
}
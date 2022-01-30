package com.shaparapatah.stopwatch.model.time

interface TimestampProvider {

    /** Интерфейс, который возвращает текущее время */

    fun getMilliseconds(): Long
}
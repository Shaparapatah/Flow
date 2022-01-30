package com.shaparapatah.stopwatch.model.repository

import com.shaparapatah.stopwatch.model.ElapsedTimeCalculator
import com.shaparapatah.stopwatch.model.StopwatchStateCalculator
import com.shaparapatah.stopwatch.model.state.StopwatchState
import com.shaparapatah.stopwatch.model.time.TimestampMillisecondsFormatter

class StopwatchStateHolder(
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    fun start() {
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = StopwatchState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}

/** Теперь мы можем управлять секундомером и хранить его текущее состояние с помощью функций
start(), pause(), stop(). Для удобства мы еще добавили функцию
getStringTimeRepresentation() : String, которая возвращает время в читабельном виде. */
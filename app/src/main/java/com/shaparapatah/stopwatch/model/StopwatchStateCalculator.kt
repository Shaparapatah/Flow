package com.shaparapatah.stopwatch.model

import com.shaparapatah.stopwatch.model.state.StopwatchState
import com.shaparapatah.stopwatch.model.time.TimestampProvider

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator
) {

    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running =
        when (oldState) {
            is StopwatchState.Running -> oldState
            is StopwatchState.Paused -> {
                StopwatchState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused =
        when (oldState) {
            is StopwatchState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                StopwatchState.Paused(elapsedTime = elapsedTime)
            }
            is StopwatchState.Paused -> oldState
        }

    /** Если состояние не меняется, то мы просто возвращаем текущее (oldState).
    Это касается каждой из двух функций. Если же в функцию пришло иное значение,
    то выполняется смена состояния:

     * для функции calculateRunningState мы будем возвращать состояние Running,
    в которое передадим текущее время и время, которое уже прошло;

     * для функции calculatePausedState мы будем возвращать состояние Paused,
    в которое передадим время, которое прошло с начала отсчета. */

}
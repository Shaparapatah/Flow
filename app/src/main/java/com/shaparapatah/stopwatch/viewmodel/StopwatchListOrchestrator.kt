package com.shaparapatah.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shaparapatah.stopwatch.model.repository.StopwatchStateHolder
import com.shaparapatah.stopwatch.model.time.TimestampMillisecondsFormatter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StopwatchListOrchestrator(
    private val stopwatchStateHolder: StopwatchStateHolder,
    private val scope: CoroutineScope,
) : ViewModel() {

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker

    private val livedata: LiveData<StopwatchStateHolder> = MutableLiveData()


    /** Функция start создает coroutine job, которая будет обновлять время для секундомера асинхронно.
    Она состоит из бесконечного цикла, в котором каждые 20 миллисекунд проверяется,
    нужна ли еще эта работа. Обновление происходит через StateFlow, который запускается из UI. */
    fun start() {
        if (job == null) startJob()
        stopwatchStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = stopwatchStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    /** Функции pause() и stop() останавливают работу корутины,
    потому что секундомер в это время не нужно обновлять. А ещё stop() обнуляет таймер.
    Работа корутины останавливается через функцию cancelChildren(), а не cancel().
    Функция cancelChildren() останавливает работу, но сохраняет scope,
    чтобы в нем можно было запускать новые корутины. Это нужно как раз на тот случай,
    если пользователь нажал не Стоп, а Пауза на секундомере. */

    fun pause() {
        stopwatchStateHolder.pause()
        stopJob()
    }

    fun stop() {
        stopwatchStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = TimestampMillisecondsFormatter.DEFAULT_TIME
    }
}
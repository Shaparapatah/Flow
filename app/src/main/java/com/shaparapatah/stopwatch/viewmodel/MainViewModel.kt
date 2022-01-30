package com.shaparapatah.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shaparapatah.stopwatch.model.ElapsedTimeCalculator
import com.shaparapatah.stopwatch.model.StopwatchStateCalculator
import com.shaparapatah.stopwatch.model.repository.StopwatchStateHolder
import com.shaparapatah.stopwatch.model.time.TimestampMillisecondsFormatter
import com.shaparapatah.stopwatch.model.time.TimestampProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MainViewModel : ViewModel() {

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    val liveData: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun start() {
        stopwatchListOrchestrator.start()
    }
    fun pause() {
        stopwatchListOrchestrator.pause()
    }
    fun stop() {
        stopwatchListOrchestrator.stop()
    }
}
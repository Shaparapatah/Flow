package com.shaparapatah.stopwatch.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shaparapatah.stopwatch.model.data.Data
import com.shaparapatah.stopwatch.model.repository.Repository

internal class MainViewModel(
    repository: Repository = Repository()
) : ViewModel() {

    val liveData: LiveData<Data> = repository.userData.asLiveData()

    /** Как только нам приходит очередная порция данных (раз в секунду), мы обновляем LiveData. */
}
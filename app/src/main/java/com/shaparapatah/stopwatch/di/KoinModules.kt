package com.shaparapatah.stopwatch

import com.shaparapatah.stopwatch.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModel = module {
    viewModel { MainViewModel() }
}
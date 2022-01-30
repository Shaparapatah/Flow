package com.shaparapatah.stopwatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shaparapatah.stopwatch.databinding.ActivityMainBinding
import com.shaparapatah.stopwatch.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val viewModel: MainViewModel by viewModel()
        mainViewModel = viewModel
        mainViewModel.liveData.observe(this) {
            binding.textTime.text = it
        }


        with(binding) {
            buttonStart.setOnClickListener {
                mainViewModel.start()
            }
            buttonPause.setOnClickListener {
                mainViewModel.pause()
            }
            buttonStop.setOnClickListener {
                mainViewModel.stop()
            }
        }
    }
}

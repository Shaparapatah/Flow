package com.shaparapatah.stopwatch.view.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shaparapatah.stopwatch.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** В Активити мы создаем ViewModel и подписываемся на изменение данных.
        Как только данные меняются — отображаем их на экране.
        Все остальное происходит внутри ViewModel */

        val textView = findViewById<TextView>(R.id.message)
        ViewModelProvider(this).get(MainViewModel::class.java).liveData.observe(
            this
        ) { dataFromDataBase ->
            textView.text = dataFromDataBase.data
        }
    }
}
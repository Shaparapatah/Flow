package com.shaparapatah.stopwatch.model.datasource

import com.shaparapatah.stopwatch.model.data.DataBase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class DataSource(
    private val dataBase: DataBase = DataBase,
    private val refreshIntervalsMs: Long = 1000
) {
    val data: Flow<String> = flow {
        while (true) {
            val dataFromDataBase = dataBase.fetchData()
            emit(dataFromDataBase.toString())
            /** emit - ф-ция испускает случайное число в виде String для всех, кто подписан на поток
             * Producer
             * Consumer
             */
            delay(refreshIntervalsMs)
            /** delay - пауза */
        }
    }
    // .flowOn(Dispatchers.Default)
    /** flowOn - явно указывает в каком потоке будет выполняться работа
     * catch - отлавливает ошибку
     */
    //  .catch { e ->
    //    println(e.message)
}

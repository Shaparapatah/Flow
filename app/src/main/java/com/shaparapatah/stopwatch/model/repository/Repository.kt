package com.shaparapatah.stopwatch.model.repository

import com.shaparapatah.stopwatch.model.data.Data
import com.shaparapatah.stopwatch.model.datasource.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class Repository(dataSource: DataSource = DataSource()) {

    val userData: Flow<Data> = dataSource.data.map { data -> Data(data) }
    //.onEach { saveInCache(it) }
    /** Оператор onEach опционален. Он показывает, что значение, возвращаемое DataSource можно
    сохранить для дальнейшего использования
    или совершить с ним неограниченное количество операций. */
}
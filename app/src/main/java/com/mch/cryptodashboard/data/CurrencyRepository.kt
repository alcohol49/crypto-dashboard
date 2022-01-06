package com.mch.cryptodashboard.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class CurrencyRepository(
    private val service: WebService,
    private val dao: CurrencyDao
) {

    fun getCurrency() = dao.getCurrencies()

    suspend fun refresh() = withContext(Dispatchers.IO) {
        val list = service.getCurrency().currencies
        delay((100L..2000L).random())
        dao.insertAll(list)
    }
}
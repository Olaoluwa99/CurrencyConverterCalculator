package com.easit.calculatorwithwidgeta.model

import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun getCurrencyData(): Flow<Result<Data>>
}
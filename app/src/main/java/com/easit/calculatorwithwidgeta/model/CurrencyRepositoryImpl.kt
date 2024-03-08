package com.easit.calculatorwithwidgeta.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class CurrencyRepositoryImpl(
    private val api: Api
): CurrencyRepository {

    private val apiKey = "fca_live_RzDDB3vgtFu22sfh9vvV7yV98zUfZLQmzkfAfdXQ"

    override suspend fun getCurrencyData(): Flow<Result<Data>> {
        return flow {
            val currencyFromApi = try {
                api.getCurrencyData(apiKey = apiKey)

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading currency"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading currency"))
                return@flow
            }  catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading currency"))
                return@flow
            }

            emit(Result.Success(currencyFromApi.data))
        }

    }

}
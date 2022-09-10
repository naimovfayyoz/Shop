package uz.fayyoz.a1shop.utill

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseNetworkRepo {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
    ): Resource<T> {
        return withContext(Dispatchers.IO)
        {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                    }
                    else -> {
                    }
                }
            } as Resource<T>
        }
    }
}
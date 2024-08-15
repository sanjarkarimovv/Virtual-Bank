package uz.androbeck.virtualbank.network

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import uz.androbeck.virtualbank.network.errors.ApiErrorType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalErrorController @Inject constructor() {
    private val errorHub = Channel<ApiErrorType>()

    fun sendError(apiErrorType: ApiErrorType) {
        errorHub.trySend(apiErrorType)
    }

    fun observeError(): Flow<ApiErrorType> {
        return errorHub.consumeAsFlow()
    }
}

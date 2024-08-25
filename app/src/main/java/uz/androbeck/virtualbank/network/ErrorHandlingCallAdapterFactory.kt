package uz.androbeck.virtualbank.network

import kotlinx.serialization.json.Json
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import uz.androbeck.virtualbank.data.dto.common.response.ErrorResDto
import uz.androbeck.virtualbank.network.errors.ApiErrorType
import uz.androbeck.virtualbank.network.errors.Error400Exception
import uz.androbeck.virtualbank.network.errors.Error404Exception
import uz.androbeck.virtualbank.network.errors.ErrorResponseEmptyException
import uz.androbeck.virtualbank.network.errors.HttpResponseException
import uz.androbeck.virtualbank.network.errors.IncorrectCredentialsException
import uz.androbeck.virtualbank.network.errors.IncorrectEndTimeProvidedException
import uz.androbeck.virtualbank.network.errors.NetworkException
import uz.androbeck.virtualbank.network.errors.OtherNetworkException
import uz.androbeck.virtualbank.network.errors.ParseErrorResponseException
import uz.androbeck.virtualbank.network.errors.UnauthorizedException
import uz.androbeck.virtualbank.network.errors.UnknownException
import uz.androbeck.virtualbank.network.errors.UserNotVerified
import java.io.IOException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandlingCallAdapterFactory @Inject constructor(
    private val json: Json, private val globalErrorController: GlobalErrorController
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        return if (returnType is ParameterizedType) {
            ErrorHandlingCallAdapter(returnType, json, globalErrorController)
        } else {
            null
        }
    }

    private class ErrorHandlingCallAdapter constructor(
        private val responseType: ParameterizedType,
        private val json: Json,
        private val globalErrorController: GlobalErrorController
    ) : CallAdapter<Any, Call<*>> {
        override fun responseType(): Type {
            return getParameterUpperBound(0, responseType)
        }

        override fun adapt(call: Call<Any>): Call<*> {
            return HttpErrorToThrowableCall(call, json, globalErrorController)
        }
    }
}

internal class HttpErrorToThrowableCall<T>(
    private val call: Call<T>,
    private val json: Json,
    private val globalErrorController: GlobalErrorController
) : Call<T> {
    override fun clone(): Call<T> {
        return HttpErrorToThrowableCall(call.clone(), json, globalErrorController)
    }

    override fun execute(): Response<T> {
        return call.execute()
    }

    override fun isExecuted(): Boolean {
        return call.isExecuted
    }

    override fun cancel() {
        call.cancel()
    }

    override fun isCanceled(): Boolean {
        return call.isCanceled
    }

    override fun request(): Request {
        return call.request()
    }

    override fun timeout(): Timeout {
        return call.timeout()
    }

    override fun enqueue(callback: Callback<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else if (response.code() == 401) {
                    callback.onFailure(call, UnauthorizedException())
                } else {
                    callback.onFailure(
                        call, getApiError(response.errorBody()?.string(), response.code())
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (t is IOException) {
                    callback.onFailure(call, NetworkException(t.message, t))
                } else {
                    t.printStackTrace()
                    callback.onFailure(call, UnknownException(t.message, t))
                }
            }
        })
    }

    private fun getApiError(body: String?, code: Int? = null): HttpResponseException {
        body ?: return ErrorResponseEmptyException()
        val errorResponse = try {
            json.decodeFromString(ErrorResDto.serializer(), body)
        } catch (e: Exception) {
            return ParseErrorResponseException(body)
        }
        ApiErrorType.getByCode(code)?.let(globalErrorController::sendError)
        return when (ApiErrorType.getByCode(code)) {
            ApiErrorType.USER_NOT_VERIFIED -> UserNotVerified(errorResponse.message.orEmpty())
            ApiErrorType.INCORRECT_CREDENTIALS -> IncorrectCredentialsException(errorResponse.message.orEmpty())
            ApiErrorType.INCORRECT_END_TIME_PROVIDED -> IncorrectEndTimeProvidedException(
                errorResponse.message.orEmpty()
            )

            null -> OtherNetworkException(errorResponse.code ?: -1)
            ApiErrorType.ERROR_RESPONSE_EMPTY -> ErrorResponseEmptyException()
            ApiErrorType.ERROR_404 -> Error404Exception(errorResponse.message)
            ApiErrorType.ERROR_401 -> TODO()
            ApiErrorType.ERROR_400 -> Error400Exception(errorResponse.message)
        }
    }
}
package uz.androbeck.virtualbank.network.errors

enum class ApiErrorType(
    val code: Int
) {
    USER_NOT_VERIFIED(101),
    INCORRECT_CREDENTIALS(102),
    INCORRECT_END_TIME_PROVIDED(103),
    ERROR_RESPONSE_EMPTY(-101);

    companion object {
        fun getByCode(code: Int?): ApiErrorType? {
            return values().firstOrNull { it.code == code }
        }
    }
}

open class HttpResponseException(
    val customCode: Int,
    override val message: String,
) : RuntimeException("Server returned custom error: $message")

class UserNotVerified(
    override val message: String
) : HttpResponseException(ApiErrorType.USER_NOT_VERIFIED.code, message)

class IncorrectCredentialsException(
    override val message: String
) : HttpResponseException(ApiErrorType.INCORRECT_CREDENTIALS.code, message)

class IncorrectEndTimeProvidedException(
    override val message: String,
) : HttpResponseException(ApiErrorType.INCORRECT_END_TIME_PROVIDED.code, message)

class OtherNetworkException(
    val code: Int
) : HttpResponseException(code, "Unknown exception")

class ParseErrorResponseException(
    val response: String
) : HttpResponseException(-100, "Unable to parse the error response: $response")

class ErrorResponseEmptyException : HttpResponseException(ApiErrorType.ERROR_RESPONSE_EMPTY.code, "Error body is empty")
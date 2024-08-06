package uz.androbeck.virtualbank.data.dto.common.response.sign_in_response

import kotlinx.serialization.SerialName

class ErrorResDto {
    @SerialName("code")
    val code: Int? = null
    @SerialName("message")
    val message: String? = null
}
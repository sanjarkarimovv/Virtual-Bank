package uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResDto(
    @SerialName("code")
    val code: Int? = null,
    @SerialName("message")
    val message: String? = null,
)

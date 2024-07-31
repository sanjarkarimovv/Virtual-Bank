package uz.androbeck.virtualbank.data.dto.common.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ErrorResDto(
    @SerialName("code") val code: Int? = null,
    @SerialName("message") val message: String? = null,
)
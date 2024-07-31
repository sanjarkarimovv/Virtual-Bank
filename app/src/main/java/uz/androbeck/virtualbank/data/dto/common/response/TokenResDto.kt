package uz.androbeck.virtualbank.data.dto.common.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResDto(
    @SerialName("token") val token: String? = null,
)

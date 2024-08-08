package uz.androbeck.virtualbank.data.dto.common.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenReqDto(
    @SerialName("token")
    val token: String? = null
)
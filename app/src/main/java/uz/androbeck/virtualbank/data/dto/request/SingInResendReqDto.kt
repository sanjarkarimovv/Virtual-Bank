package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingInResendReqDto(
    @SerialName("token")
    val token: String? = null
)

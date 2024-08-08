package uz.androbeck.virtualbank.data.dto.common.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CodeVerifyReqDto(
    @SerialName("token")
    val token: String? = null,
    @SerialName("code")
    val code: String? = null,
)

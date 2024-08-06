package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInVerifyReqDto(
    @SerialName("refresh-token")
    val refreshToken: String? = null,
    @SerialName("access-token")
    val accessToken: String? = null
)

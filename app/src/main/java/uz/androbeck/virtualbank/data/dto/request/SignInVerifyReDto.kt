package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInVerifyReDto(
    @SerialName("token")
    val token: String? = null,
    @SerialName("code")
    val code: String? = null
) {
}
package uz.androbeck.virtualbank.data.dto.request.sign_in

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInReqDto(
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("password")
    val password: String? = null
)

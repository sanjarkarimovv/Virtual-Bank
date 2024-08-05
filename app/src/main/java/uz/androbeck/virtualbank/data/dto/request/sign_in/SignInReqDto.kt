package uz.androbeck.virtualbank.data.dto.request.sign_in

import kotlinx.serialization.SerialName

data class SignInReqDto(
    @SerialName("password")
    val password: String? = null,
    @SerialName("phone")
    val phone: String? = null
)

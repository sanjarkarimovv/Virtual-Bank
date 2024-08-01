package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.Serializable


@Serializable
data class SignInReqDto (
    val phone: String? = null,
    val password: String? = null
)
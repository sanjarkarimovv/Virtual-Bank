package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpReqDto(
    val phone: String? = null,
    val password: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val bornDate: String? = null,
    val gender: String? = null
)

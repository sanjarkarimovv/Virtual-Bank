package uz.androbeck.virtualbank.data.dto.common.response.sign_in_response

import kotlinx.serialization.SerialName

class TokenResDto(
    @SerialName("token") val token: String? = null
)
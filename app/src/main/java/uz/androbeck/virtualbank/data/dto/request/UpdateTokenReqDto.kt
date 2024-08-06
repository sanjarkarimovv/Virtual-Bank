package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateTokenReqDto(
    @SerialName("refresh-token")
    val refresh_token: String? = null
)
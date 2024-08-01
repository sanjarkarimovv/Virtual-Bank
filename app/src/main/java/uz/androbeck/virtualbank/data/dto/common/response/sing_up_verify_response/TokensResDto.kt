package uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensResDto(
    @SerialName("refresh-token")
    val refresh_token: String? = null,
    @SerialName("access-token")
    val access_token: String? = null,
)

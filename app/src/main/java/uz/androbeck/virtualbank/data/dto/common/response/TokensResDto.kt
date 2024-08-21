package uz.androbeck.virtualbank.data.dto.common.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokensResDto(
    @SerializedName("refresh-token")
    val refresh_token: String? = null,
    @SerializedName("access-token")
    val access_token: String? = null,
)

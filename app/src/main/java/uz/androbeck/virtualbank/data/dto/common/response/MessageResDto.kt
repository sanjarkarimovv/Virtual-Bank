package uz.androbeck.virtualbank.data.dto.common.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResDto (
    @SerialName("message") val message: String? = null
)
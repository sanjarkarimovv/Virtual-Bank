package uz.androbeck.virtualbank.data.dto.request.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCardOwnerByPanReqDto(
    @SerialName("pan")
    val pan: String? = null
)

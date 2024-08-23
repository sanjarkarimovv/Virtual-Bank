package uz.androbeck.virtualbank.data.dto.response.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCardOwnerByPanResDto(
    @SerialName("pan")
    val pan: String? = null
)
package uz.androbeck.virtualbank.data.dto.response.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LastTransfersResDto(
    @SerialName("transfers")
   val transferResDto: List<InComeAndOutComeResDto>?=null,
)



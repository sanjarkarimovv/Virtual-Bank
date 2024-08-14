package uz.androbeck.virtualbank.data.dto.response.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto

@Serializable
data class LastTransfersResDto(
    @SerialName("transfers")
   val transferResDto: List<InComeAndOutComeResDto>?=null,
)



package uz.androbeck.virtualbank.data.dto.response.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class LastTransfersResDto(
    @SerialName("transfers")
   val transferResDto: List<InComeAndOutComeResDto>?=null,
)

@Serializable
data class InComeAndOutComeResDto(
    @SerialName("type")
    val type: String?=null,
    @SerialName("from")
    val from: String?=null,
    @SerialName("to")
    val to: String?=null,
    @SerialName("amount")
    val amount: Double=0.0,
    @SerialName("time")
    val time: Long=0
)

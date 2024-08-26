package uz.androbeck.virtualbank.data.dto.common.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InComeAndOutComeResDto(
    @SerialName("type")
    val type: String? = null,
    @SerialName("from")
    val from: String? = null,
    @SerialName("to")
    val to: String? = null,
    @SerialName("amount")
    val amount: Long = 0,
    @SerialName("time")
    val time: Long = 0
)
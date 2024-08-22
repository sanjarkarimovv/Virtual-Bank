package uz.androbeck.virtualbank.data.dto.request.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransferRequestDto(
    @SerialName("type")
    val type: String? = null,
    @SerialName("sender-id")
    val sender_id: String? = null,
    @SerialName("receiver-pan")
    val receiver_pan: String? = null,
    @SerialName("amount")
    val amount: Long? = null,
)

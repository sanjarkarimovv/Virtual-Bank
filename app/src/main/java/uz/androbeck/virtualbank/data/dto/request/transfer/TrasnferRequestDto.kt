package uz.androbeck.virtualbank.data.dto.request.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrasnferRequestDto(
    val type  : String? = null,
    @SerialName("sender-id")
    val sender_id : String? = null,
    @SerialName("receiver-id")
    val receiver_id : String? = null,
    val amount : Double? = null
)

package uz.androbeck.virtualbank.data.dto.request.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFeeReqDto (
    @SerialName("sender-id")
    val sender_id: String? = null,
    @SerialName("receiver")
    val receiver: String? = null,
    @SerialName("amount")
    val amount: String? = null
)
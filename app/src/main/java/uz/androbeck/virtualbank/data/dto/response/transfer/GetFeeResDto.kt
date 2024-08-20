package uz.androbeck.virtualbank.data.dto.response.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetFeeResDto(
    @SerialName("fee")
    val fee: Long? = null,
    @SerialName("amount")
    val amount: Long? = null
)
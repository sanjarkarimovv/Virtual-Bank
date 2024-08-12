package uz.androbeck.virtualbank.data.dto.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalBalanceResDto(
    @SerialName("total-balance")
    val total_balance: String? = null,
) {
}
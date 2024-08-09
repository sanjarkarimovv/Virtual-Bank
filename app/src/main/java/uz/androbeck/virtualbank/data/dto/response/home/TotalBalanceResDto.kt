package uz.androbeck.virtualbank.data.dto.response.home

import kotlinx.serialization.SerialName

data class TotalBalanceResDto(
    @SerialName("total-balance")
    val total_balance: String? = null,
) {
}
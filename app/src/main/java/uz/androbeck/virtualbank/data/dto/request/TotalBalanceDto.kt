package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName

data class TotalBalanceDto(
    @SerialName("total_balance")
    val totalBalance: Float?=null
)
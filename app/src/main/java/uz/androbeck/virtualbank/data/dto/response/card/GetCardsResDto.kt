package uz.androbeck.virtualbank.data.dto.response.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCardsResDto(
    @SerialName("cards")
    val cardResDto: List<CardResDto>?=null,
)

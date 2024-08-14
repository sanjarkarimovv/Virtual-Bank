package uz.androbeck.virtualbank.data.dto.response.cards

import kotlinx.serialization.SerialName

data class CardsResDto(
    @SerialName("cardsDto")
    val cardsDto: List<InComeCardResDto>? = null
)

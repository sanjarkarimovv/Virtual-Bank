package uz.androbeck.virtualbank.domain.ui_models.cards

import uz.androbeck.virtualbank.data.api.CardsService

data class GetCardsUIModel (
    val cardUIModel: List<CardInfoUIModel>? = null,
)
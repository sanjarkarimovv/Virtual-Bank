package uz.androbeck.virtualbank.domain.ui_models.home

import uz.androbeck.virtualbank.ui.screens.HomeComponents

sealed class HomeBodyModels {
    data class Card(
        val name: HomeComponents,
        val data: List<CardModel>
    ) : HomeBodyModels()
}
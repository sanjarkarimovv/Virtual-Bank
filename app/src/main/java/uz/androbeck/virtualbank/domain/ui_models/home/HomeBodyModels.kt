package uz.androbeck.virtualbank.domain.ui_models.home

import uz.androbeck.virtualbank.ui.screens.HomeComponents

sealed class HomeBodyModels {
    data class Card(
        val name: HomeComponents,
        val data: List<CardModel>
    ) : HomeBodyModels()

    data class Payment(
        val name: HomeComponents,
        val data: List<PaymentsModel>
    ):HomeBodyModels()

    data class LastTransfer(
        val name: HomeComponents,
        val data: List<LastTransferModel>
    ) : HomeBodyModels()
}
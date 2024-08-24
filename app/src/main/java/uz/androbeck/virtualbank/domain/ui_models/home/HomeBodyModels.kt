package uz.androbeck.virtualbank.domain.ui_models.home

import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.ui.screens.HomeComponents

sealed class HomeBodyModels {
    data class Card(
        val name: HomeComponents,
        val data: List<CardUIModel>
    ) : HomeBodyModels()

    data class Payment(
        val name: HomeComponents,
        val data: List<PaymentsModel>
    ):HomeBodyModels()

    data class LastTransfer(
        val name: HomeComponents,
        val data: List<InComeAndOutComeUIModel>
    ) : HomeBodyModels()

    data class Advertising(
        val data: List<AdvertisingModel>?=null,
        val error: String? = null,
        val loading: Boolean? = null

    ) : HomeBodyModels()

    data class TotalBalance(val amount: String):HomeBodyModels()
    data class Error(val massage: String):HomeBodyModels()
}
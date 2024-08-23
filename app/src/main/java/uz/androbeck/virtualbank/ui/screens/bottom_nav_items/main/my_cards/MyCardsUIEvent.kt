package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.domain.ui_models.cards.GetCardsUIModel
import uz.androbeck.virtualbank.ui.screens.auth.login.LoginUiEvent

sealed class MyCardsUIEvent {
    data class Success(val cards: List<CardUIModel>) : MyCardsUIEvent()
    data class Error(val massage: String?): MyCardsUIEvent()}
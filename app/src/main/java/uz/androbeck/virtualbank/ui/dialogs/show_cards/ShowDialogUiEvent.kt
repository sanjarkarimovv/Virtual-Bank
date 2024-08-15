package uz.androbeck.virtualbank.ui.dialogs.show_cards

import uz.androbeck.virtualbank.domain.ui_models.home.CardModel

sealed class ShowDialogUiEvent {
    data class AllCards(val list: List<CardModel>) : ShowDialogUiEvent()
}
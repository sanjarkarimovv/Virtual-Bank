package uz.example.royxat

import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel

interface CardListener {
    fun onDeleteCard(card: CardUIModel)
}
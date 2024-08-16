package uz.androbeck.virtualbank.domain.ui_models.card

import uz.androbeck.virtualbank.ui.enums.CardType

data class GetCardUIModel(
    val cardName: String? = null,
    val amount: String? = null,
    val owner: String? = null,
    val pan: String? = null,
    val expiredYear: String? = null,
    val expiredMonth: String? = null,
    val themeType: String? = null,
    val cardType: CardType
)

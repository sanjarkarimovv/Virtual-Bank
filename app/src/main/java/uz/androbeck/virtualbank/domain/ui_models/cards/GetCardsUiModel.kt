package uz.androbeck.virtualbank.domain.ui_models.cards

data class GetCardsUiModel(
    val name: String? = null,
    val amount: Long? = null,
    val owner: String? = null,
    val pan: String? = null,
    val expiredYear: Int? = null,
    val expiredMonth: Int? = null,
)

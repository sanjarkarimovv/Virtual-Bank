package uz.androbeck.virtualbank.domain.ui_models.cards

data class CardUIModel (
    val id: Int? = 0,
    val amount: Double? = 0.0,
    val owner: String? = null,
    val pan: Double? = 0.0,
    val expiredYear: Int? = 0,
    val expiredMonth: Int? = 0,
    val themeType: Int? = 0,
    val isVisible: Boolean? = false

)


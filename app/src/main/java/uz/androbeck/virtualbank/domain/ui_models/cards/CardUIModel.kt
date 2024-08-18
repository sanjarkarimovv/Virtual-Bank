package uz.androbeck.virtualbank.domain.ui_models.cards

data class CardUIModel(
    val id: Int? = 0,
    val amount: Double? = 0.0,
    val name: String? = null,
    val owner: String? = null,
    val pan: String? = null,
    val expiredYear: Int? = 0,
    val expiredMonth: Int? = 0,
    val themeType: Int? = 0,
    val isVisible: Boolean? = false
)

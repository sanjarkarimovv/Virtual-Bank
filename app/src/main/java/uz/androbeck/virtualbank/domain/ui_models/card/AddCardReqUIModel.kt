package uz.androbeck.virtualbank.domain.ui_models.card

data class AddCardReqUIModel(
    val pan: String? = null,
    val expiredYear: String? = null,
    val expiredMonth: String? = null,
    val name: String? = null
)

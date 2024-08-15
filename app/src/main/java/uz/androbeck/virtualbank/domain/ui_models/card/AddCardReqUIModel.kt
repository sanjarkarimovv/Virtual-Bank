package uz.androbeck.virtualbank.domain.ui_models.card

data class AddCardReqUIModel(
    val pan: String? = null,
    val expired_year: String? = null,
    val expired_month: String? = null,
    val name: String? = null
)

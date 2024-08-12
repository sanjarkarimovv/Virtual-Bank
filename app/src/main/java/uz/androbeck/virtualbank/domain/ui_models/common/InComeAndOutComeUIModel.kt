package uz.androbeck.virtualbank.domain.ui_models.common

data class InComeAndOutComeUIModel(
    val type: String? = null,
    val from: String? = null,
    val to: String? = null,
    val amount: Double = 0.0,
    val time: Long = 0,

)

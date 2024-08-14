package uz.androbeck.virtualbank.domain.ui_models.cards

import kotlinx.serialization.SerialName

data class CardInfoUIModel(
    val id: Long = 0,
    val name: String? = null,
    val amount: Double = 0.0,
    val owner: String? = null,
    val pan: Int = 0,
    val expiredYear: Long=0,
    val expiredMonth: Long = 0,
    val themeType: Long = 0,
    val isVisible: Boolean? = false
)

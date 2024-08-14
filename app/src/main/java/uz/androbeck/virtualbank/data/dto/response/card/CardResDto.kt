package uz.androbeck.virtualbank.data.dto.response.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResDto(
    @SerialName("id")
    val id: Int? = 0,
    @SerialName("amount")
    val amount: Double? = 0.0,
    @SerialName("owner")
    val owner: String? = null,
    @SerialName("pan")
    val pan: Double? = 0.0,
    @SerialName("expired-year")
    val expiredYear: Int? = 0,
    @SerialName("expired-month")
    val expiredMonth: Int? = 0,
    @SerialName("theme-type")
    val themeType: Int? = 0,
    @SerialName("is-visible")
    val isVisible: Boolean? = false
)

package uz.androbeck.virtualbank.data.dto.response.cards

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InComeCardResDto(
    @SerialName("id")
    val id: Long = 0,
    @SerialName("name")
    val name: String? = null,
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("owner")
    val owner: String? = null,
    @SerialName("pan")
    val pan: Int = 0,
    @SerialName("expired-year")
    val expiredYear: Long=0,
    @SerialName("expired-month")
    val expiredMonth: Long = 0,
    @SerialName("theme-type")
    val themeType: Long = 0,
    @SerialName("is-visible")
    val isVisible: Boolean? = false
)

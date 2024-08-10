package uz.androbeck.virtualbank.data.dto.response.cards

import kotlinx.serialization.SerialName

data class GetCardsResDto(
    val id: Int? = null,
    val name: String? = null,
    val amount: Long? = null,
    val owner: String? = null,
    val pan: String? = null,
    @SerialName("expired-year")
    val expired_year: Int? = null,
    @SerialName("expired-month")
    val expired_month: Int? = null,
    @SerialName("theme-type")
    val theme_type: Int? = null,
    @SerialName("is-visible")
    val is_visible: Boolean? = null
)
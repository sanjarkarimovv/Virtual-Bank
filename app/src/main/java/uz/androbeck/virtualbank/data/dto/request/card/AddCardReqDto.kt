package uz.androbeck.virtualbank.data.dto.request.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class AddCardReqDto (
    @SerialName("pan")
    val pan: String? = null,
    @SerialName("expired-year")
    val expired_year: String? = null,
    @SerialName("expired-month")
    val expired_month: String? = null,
    @SerialName("name")
    val name: String? = null

)
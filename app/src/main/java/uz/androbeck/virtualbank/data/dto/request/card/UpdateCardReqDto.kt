package uz.androbeck.virtualbank.data.dto.request.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateCardReqDto(
    val id: Int? = null,
    val name: String? = null,
    @SerialName("theme-type")
    val theme_type: Int? = null,
    @SerialName("is-visible")
    val is_visible: Boolean? = null
)
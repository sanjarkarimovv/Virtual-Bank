package uz.androbeck.virtualbank.data.dto.request.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateInfoReqDto(
    @SerialName("first-name")
    val first_name: String? = null,
    @SerialName("last-name")
    val last_name: String? = null,
    @SerialName("gender-type")
    val gender_type: String? = null,
    @SerialName("born-date")
    val born_date: String? = null
)
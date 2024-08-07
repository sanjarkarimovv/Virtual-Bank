package uz.androbeck.virtualbank.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FullInfoDto(
    @SerialName("phone")
    val phone: String? = null,
    @SerialName("first-name")
    val first_name: String? = null,
    @SerialName("last-name")
    val last_name: String? = null,
    @SerialName("born-date")
    val born_date: String? = null,
    @SerialName("gender")
    val gender: String? = null
)

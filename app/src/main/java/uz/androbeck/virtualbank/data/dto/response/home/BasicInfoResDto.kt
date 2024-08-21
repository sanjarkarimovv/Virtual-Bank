package uz.androbeck.virtualbank.data.dto.response.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasicInfoResDto(
    @SerialName("firsrt-name")
    val first_name: String? = null,
    @SerialName("gender-type")
    val gender_type: Int? = null,
    val age: Int? = null,
)
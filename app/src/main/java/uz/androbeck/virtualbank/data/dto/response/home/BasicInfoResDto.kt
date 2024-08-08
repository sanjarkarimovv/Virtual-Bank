package uz.androbeck.virtualbank.data.dto.response.home

import kotlinx.serialization.SerialName

data class BasicInfoResDto(
    @SerialName("first-name")
    val first_name: String? = null,
    @SerialName("gender-type")
    val gender_type: String? = null,
    val age: String? = null,
)
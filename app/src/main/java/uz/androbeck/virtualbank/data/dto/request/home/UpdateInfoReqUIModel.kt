package uz.androbeck.virtualbank.data.dto.request.home

import kotlinx.serialization.SerialName

data class UpdateInfoReqUIModel(
    @SerialName("first-name")
    val firstName: String? = null,
    @SerialName("last-name")
    val lastName: String? = null,
    @SerialName("born-date")
    val bornDate: String? = null,
    val gender: String? = null
)
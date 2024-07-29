package uz.androbeck.virtualbank.data.dto.request

import com.google.gson.annotations.SerializedName

data class SignUpReqDto(
    val phone: String? = null,
    val password: String? = null,
    @SerializedName("first-name")
    val first_name: String? = null,
    @SerializedName("last-name")
    val last_name: String? = null,
    @SerializedName("born-date")
    val born_date: String? = null,
    val gender: String? = null
)

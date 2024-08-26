package uz.androbeck.virtualbank.data.dto.request.card

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class DeleteCardReqDto (
    @SerialName("id")
    val id: String? = null,


)
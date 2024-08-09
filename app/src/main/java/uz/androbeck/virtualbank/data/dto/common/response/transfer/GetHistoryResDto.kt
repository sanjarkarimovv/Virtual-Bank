package uz.androbeck.virtualbank.data.dto.common.response.transfer

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetHistoryResDto(
    @SerialName("total-elements")
    val totalElements: String? = null,
    @SerialName("total-pages")
    val totalPages: String? = null,
    @SerialName("current-page")
    val currentPage: String? = null,
    @SerialName("child")
    val child: List<Child>
)

@Serializable
data class Child(
    @SerialName("type")
    val type: String,
    @SerialName("from")
    val from: String,
    @SerialName("to")
    val to: String,
    @SerialName("amount")
    val amount: Int,
    @SerialName("time")
    val time: Long
)

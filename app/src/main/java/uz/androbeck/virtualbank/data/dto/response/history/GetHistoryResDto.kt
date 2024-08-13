package uz.androbeck.virtualbank.data.dto.response.history

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
    val child: List<InComeAndOutComeResDto>
)



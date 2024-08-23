package uz.androbeck.virtualbank.data.dto.response.history

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto

@Serializable
data class GetHistoryResDto(
    @SerialName("total-elements")
    val total_elements: Int? = null,
    @SerialName("total-pages")
    val total_pages: Int? = null,
    @SerialName("current-page")
    val current_page: Int? = null,
    @SerialName("child")
    val transferResDto: List<InComeAndOutComeResDto>? = null
)
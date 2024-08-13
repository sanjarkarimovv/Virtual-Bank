package uz.androbeck.virtualbank.data.dto.response.history

import kotlinx.serialization.SerialName
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto

data class GetHistoryResDto (
    @SerialName("total-elements")
    val total_elements: Int?=null,
    @SerialName("total-pages")
    val total_pages: Int?=null,
    @SerialName("current-page")
    val current_page: Int?=null,
    @SerialName("transfers")
    val transferResDto: List<InComeAndOutComeResDto>?=null,

    )
package uz.androbeck.virtualbank.domain.ui_models.history

import uz.androbeck.virtualbank.domain.ui_models.common.InComeAndOutComeUIModel

data class GetHistoryUIModel(
    val total_elements: Int? = null,
    val total_pages: Int? = null,
    val current_page: Int? = null,
    val transfers: List<InComeAndOutComeUIModel>? = null,
    )

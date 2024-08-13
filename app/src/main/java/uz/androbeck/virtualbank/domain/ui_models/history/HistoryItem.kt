package uz.androbeck.virtualbank.domain.ui_models.history

import uz.androbeck.virtualbank.data.dto.common.response.transfer.Child

sealed class HistoryItem {
    data class Header(val time: Long?) : HistoryItem()
    data class Content(val child: Child) : HistoryItem()
}
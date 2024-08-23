package uz.androbeck.virtualbank.domain.ui_models.history

sealed class HistoryItem {
    data class Header(val time: Long?) : HistoryItem()
    data class Content(val child: InComeAndOutComeUIModel) : HistoryItem()
}
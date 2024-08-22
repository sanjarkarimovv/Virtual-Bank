package uz.androbeck.virtualbank.ui.customViews.forHome

sealed class HeaderUiEvent {
    data class ClickShowAmount(val isShow: Boolean) : HeaderUiEvent()
    data object ClickMore : HeaderUiEvent()
    data object ClickNfs : HeaderUiEvent()
    data object ClickCards : HeaderUiEvent()
    data object ClickQR : HeaderUiEvent()
}
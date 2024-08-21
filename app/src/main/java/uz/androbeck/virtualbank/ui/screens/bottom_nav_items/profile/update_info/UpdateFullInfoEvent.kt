package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

sealed class UpdateFullInfoEvent {
    data object Loading : UpdateFullInfoEvent()
    data class Success(val successMessage: String) : UpdateFullInfoEvent()
    data class Error(val error: String) : UpdateFullInfoEvent()
}
package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update

import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel

sealed class UpdateProfileEvent {
    data object Loading : UpdateProfileEvent()
    data class Success(val model: FullInfoUIModel?) : UpdateProfileEvent()
    data class Error(val massage: String?) : UpdateProfileEvent()
}
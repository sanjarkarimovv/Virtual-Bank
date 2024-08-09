package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel

sealed class ProfileUIEvent {
    data object Loading : ProfileUIEvent()
    data class Success(val fullInfoUIModel: FullInfoUIModel) : ProfileUIEvent()
    data class Error(val massage: String?) : ProfileUIEvent()

}
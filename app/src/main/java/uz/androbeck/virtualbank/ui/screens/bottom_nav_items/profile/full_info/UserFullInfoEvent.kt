package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.full_info

import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel

sealed class UserFullInfoEvent {
    data object Loading : UserFullInfoEvent()
    data class Success(val model: FullInfoUIModel?) : UserFullInfoEvent()
    data class Error(val massage: String?) : UserFullInfoEvent()
}
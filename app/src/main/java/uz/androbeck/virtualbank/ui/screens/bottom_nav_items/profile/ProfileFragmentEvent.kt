package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel

sealed class ProfileFragmentEvent {
    data object Loading : ProfileFragmentEvent()
    data class Success(val model: FullInfoUIModel?) : ProfileFragmentEvent()
    data class Error(val massage: String?) : ProfileFragmentEvent()
}
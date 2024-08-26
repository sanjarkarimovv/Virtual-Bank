package uz.androbeck.virtualbank.data.dto.response.home.fireBaseResDto

import uz.androbeck.virtualbank.domain.ui_models.home.AdvertisingModel

sealed class GetTvBannerResEvent {
    data object Loading : GetTvBannerResEvent()
    data class Success(val data: List<AdvertisingModel>) : GetTvBannerResEvent()
    data class Error(val message: String) : GetTvBannerResEvent()
}
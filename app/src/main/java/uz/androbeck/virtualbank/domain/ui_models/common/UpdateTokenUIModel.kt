package uz.androbeck.virtualbank.domain.ui_models.common

data class UpdateTokenUIModel(
    var refreshToken: String? = null,
    var accessToken: String? = null
)
package uz.androbeck.virtualbank.domain.ui_models.authentication

data class UpdateTokenReqUIModel (
    var refreshToken: String? = null,
    var accessToken: String? = null
)
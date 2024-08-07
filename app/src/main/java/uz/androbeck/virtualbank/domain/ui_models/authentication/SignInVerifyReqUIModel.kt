package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignInVerifyReqUIModel(
    val accessToken: String? = null,
    val refreshToken: String? = null
)

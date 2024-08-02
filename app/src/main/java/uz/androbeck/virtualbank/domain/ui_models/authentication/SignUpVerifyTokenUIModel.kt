package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignUpVerifyTokenUIModel(
    val refreshToken: String? = null,
    val accessToken: String? = null
)

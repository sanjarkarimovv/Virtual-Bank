package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignInVerifyReqUIModel(
    val token: String? = null,
    val code: String? = null
)

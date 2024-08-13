package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignUpVerifyReqUIModel(
    val token: String? = null,
    val code: String? = null
)

package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignUpReqUIModel(
    val phone: String? = null,
    val password: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val bornDate: String? = null,
    val gender: String? = null
)

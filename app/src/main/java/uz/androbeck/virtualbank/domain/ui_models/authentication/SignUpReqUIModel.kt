package uz.androbeck.virtualbank.domain.ui_models.authentication

data class SignUpReqUIModel(
    var phone: String? = null,
    var password: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var bornDate: String? = null,
    var gender: Int? = null
)

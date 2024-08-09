package uz.androbeck.virtualbank.ui.screens.auth.login

 sealed class LoginUiEvent {
    data object Loading : LoginUiEvent()
    data class Success(val token:String?) : LoginUiEvent()
    data class Error(val massage: String?):LoginUiEvent()}
package uz.androbeck.virtualbank.ui.screens.auth.login

 sealed class LoginUiEvent {
    data object Loading : LoginUiEvent()
    data object Success : LoginUiEvent()
    data class Error(val massage: String?):LoginUiEvent()}
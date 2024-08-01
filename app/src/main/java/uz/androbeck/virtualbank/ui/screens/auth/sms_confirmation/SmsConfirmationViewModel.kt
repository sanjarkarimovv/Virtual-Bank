package uz.androbeck.virtualbank.ui.screens.auth.sms_confirmation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyTokenUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpVerifyUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SmsConfirmationViewModel @Inject constructor(
    private val signUpVerifyUseCase: SignUpVerifyUseCase,
) : BaseViewModel() {

    private val _signUpVerifyEvent = MutableStateFlow(SignUpVerifyTokenUIModel())
    val signUpVerifyEvent: StateFlow<SignUpVerifyTokenUIModel> = _signUpVerifyEvent.asStateFlow()

    init {
        signUpVerify()
    }

    private fun signUpVerify() {
        viewModelScope.launch(Dispatchers.IO) {
            val signUpVerifyReqUIModel = SignUpVerifyReqUIModel(
                token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJodHRwOi8vMTI3LjAuMC4xOjgwODQvbW9iaWxlLWJhbmsvdjEvYXV0aCIsImlzcyI6Imh0dHA6Ly8xMjcuMC4wLjE6ODA4NC9tb2JpbGUtYmFuayIsInBob25lIjoiOTcxNzE0MjQwIiwiY29kZSI6IjM4MTIwNiIsImJvZHkiOiJTYW5qYXIjS2FyaW1vdiMxMjM0cXdlUiMyMTMyNDMyNDMyNDMjMSMiLCJleHAiOjE3MjI1MTMxOTN9.MjbWO2aDLBXKnW9gsXmUKWekVcdNUwkizQUf8oSdXzk",
                code = "118367"
            )
            signUpVerifyUseCase(signUpVerifyReqUIModel)
                .onEach {
                    println(":::::::::SignUpVerifyUseCase ViewModel -> $it")
                }
                .launchIn(viewModelScope)
        }
    }
}
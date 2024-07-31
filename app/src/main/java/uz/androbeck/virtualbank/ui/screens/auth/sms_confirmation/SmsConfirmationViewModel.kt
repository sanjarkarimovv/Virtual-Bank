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
                token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsvYXV0aCIsImNvZGUiOiIxMTgzNjciLCJwaG9uZSI6Iis5OTg5OTM5NDYyODAiLCJpc3MiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsiLCJib2R5IjoiTXVoYW1tYWRhbGkjUmFoaW1iZXJnYW5vdiNxd2VydHkjOTY5ODIyMDAwMDAwIzAjIiwiZXhwIjoxNjcxMTA3NzA2fQ.FDQAdueKD8gsWwlekOoezWhNixPNUKj5NElbfbRSOxM",
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
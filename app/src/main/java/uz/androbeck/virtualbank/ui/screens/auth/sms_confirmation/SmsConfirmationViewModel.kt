package uz.androbeck.virtualbank.ui.screens.auth.sms_confirmation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.androbeck.virtualbank.domain.ui_models.common.TokensUIModel
import uz.androbeck.virtualbank.domain.useCases.authentication.SignUpVerifyUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SmsConfirmationViewModel @Inject constructor(
    private val signUpVerifyUseCase: SignUpVerifyUseCase,
) : BaseViewModel() {

    private val _signUpVerifyEvent = MutableStateFlow(TokensUIModel())
    val signUpVerifyEvent: StateFlow<TokensUIModel> = _signUpVerifyEvent.asStateFlow()

    init {
        signUpVerify()
    }

    private fun signUpVerify() {

    }
}
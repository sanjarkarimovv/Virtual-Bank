package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel() {




    private val _signInEvent = MutableStateFlow(TokenUIModel())
    val signInEvent: StateFlow<TokenUIModel> = _signInEvent.asStateFlow()

    init{
        singIn()
    }

    private fun singIn() {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }

    private val _signUpEvent = MutableStateFlow(TokenUIModel())
    val signUpEvent: StateFlow<TokenUIModel> = _signUpEvent.asStateFlow()

    init {
        signUp()
    }

    private fun signUp() {
        viewModelScope.launch (Dispatchers.IO){
            val signUpReqUIModel = SignUpReqUIModel(
                phone = "+998971714240",
                password = "1234qweR",
                firstName = "Sanjar",
                lastName = "Karimov",
                bornDate = "213243243243",
                gender = "1"
            )
            signUpUseCase(signUpReqUIModel)
                .onEach {
                    println(":::AAAA -> $it")
                }
                .launchIn(viewModelScope)
        }
    }
}
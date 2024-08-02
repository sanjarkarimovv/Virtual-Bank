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
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignInUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {

    private val _signInEvent = MutableStateFlow(TokenUIModel())
    val signInEvent: StateFlow<TokenUIModel> = _signInEvent.asStateFlow()

    init {
        signIn()
    }

    private fun signIn() {
        viewModelScope.launch(Dispatchers.IO) {
            val singInReqUIModel = SignInReqUIModel(
                phone = "+998971714240",// or "+998971714240",
                password = "1234qweR" // or "1234qweR"
            )
            signInUseCase.invoke(singInReqUIModel)
                .onEach {
                    println(":::BBBBBBBBB -> $it")
                }.launchIn(viewModelScope)
        }
    }
}
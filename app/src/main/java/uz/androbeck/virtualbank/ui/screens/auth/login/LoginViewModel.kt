package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignInUseCase
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel() {
    private val _signInEvent = Channel<Unit>()
    val signInEvent = _signInEvent.consumeAsFlow()
    fun signIn(requestUIModel: SignInReqUIModel) {
        signInUseCase(requestUIModel).onEach {
            _signInEvent.trySend(Unit)
        }.launchIn(viewModelScope)
    }
}
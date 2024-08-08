package uz.androbeck.virtualbank.ui.screens.auth.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.domain.useCases.authentication.SignInUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val errorHandler: ErrorHandler,
) : BaseViewModel() {
    private val _signInEvent = Channel<LoginUiEvent>()
    val signInEvent = _signInEvent.consumeAsFlow()

    private val _accessLogin = Channel<Triple<Boolean, String?, SignInReqUIModel?>>()
    val accessLogin = _accessLogin.consumeAsFlow()

    fun signIn(signUpReqUIModel: SignInReqUIModel) {
        _signInEvent.trySend(LoginUiEvent.Loading)
        signInUseCase.invoke(signUpReqUIModel).onEach {
            _signInEvent.trySend(LoginUiEvent.Success(it.token))
        }.catch {
            errorHandler.handleError(it)
            _signInEvent.trySend(LoginUiEvent.Error(it.message))
        }.launchIn(viewModelScope)
    }

    fun accessCheck(signInReqUIModel: SignInReqUIModel) {
        when {
            signInReqUIModel.password.isNullOrEmpty() -> {
                _accessLogin.trySend(Triple(false, "Password empty", null))
            }

            signInReqUIModel.phone.isNullOrEmpty() -> {
                _accessLogin.trySend(Triple(false, "phone number empty", null))
            }

            else -> {
                _accessLogin.trySend(Triple(true, null, signInReqUIModel))
            }
        }
    }

}
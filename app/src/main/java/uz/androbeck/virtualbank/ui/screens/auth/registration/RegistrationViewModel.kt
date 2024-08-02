package uz.androbeck.virtualbank.ui.screens.auth.registration

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val preferencesProvider: PreferencesProvider,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    private val _signUpEvent = Channel<Unit>()
    val signUpEvent = _signUpEvent.consumeAsFlow()

    private val _accessSignUp =
        MutableStateFlow<Triple<Boolean, String, SignUpReqUIModel?>>(Triple(false, "", null))
    val accessSignUp = _accessSignUp.asStateFlow()

    fun accessSignUp(requestModel: SignUpReqUIModel, confirmPassword: String?) {
        if (requestModel.firstName.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "First name is empty", null)
            return
        }
        if (requestModel.lastName.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "Last name is empty", null)
            return
        }
        if (requestModel.password.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "Password is empty", null)
            return
        }
        if (confirmPassword.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "Confirm password is empty", null)
            return
        }
        if (requestModel.password != confirmPassword) {
            _accessSignUp.value = Triple(false, "Passwords do not match", null)
            return
        }
        if (requestModel.phone.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "Phone number is empty", null)
            return
        }
        if (requestModel.bornDate.isNullOrEmpty()) {
            _accessSignUp.value = Triple(false, "Born date is empty", null)
            return
        }
        _accessSignUp.value = Triple(true, "", requestModel)
    }

    fun signUp(requestModel: SignUpReqUIModel) {
        signUpUseCase(requestModel).onEach {
            it.token?.let { token ->
                preferencesProvider.token = token
            }
            _signUpEvent.trySend(Unit)
        }.catch {
            errorHandler.handleError(it)
        }.launchIn(viewModelScope)
    }
}
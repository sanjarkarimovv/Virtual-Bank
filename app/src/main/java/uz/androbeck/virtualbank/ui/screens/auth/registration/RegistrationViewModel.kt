package uz.androbeck.virtualbank.ui.screens.auth.registration

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpUseCase
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.base.Resource
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase, private val messageController: MessageController
) : BaseViewModel() {

    private val _signUpEvent = MutableStateFlow<Resource>(Resource.Loading)
    val signUpEvent = _signUpEvent.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        messageController.observeMessage().onEach {
            _errorMessage.value = it
        }.launchIn(viewModelScope)
    }

    fun signUp(requestModel: SignUpReqUIModel) {
        signUpUseCase(requestModel).onEach {
            _signUpEvent.value = Resource.Success(it)
        }.launchIn(viewModelScope)
    }
}
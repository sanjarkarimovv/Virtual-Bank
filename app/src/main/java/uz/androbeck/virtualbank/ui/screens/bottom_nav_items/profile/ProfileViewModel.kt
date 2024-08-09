package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.screens.auth.login.LoginUiEvent
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {


    private val _profileEvent = Channel<ProfileUIEvent>()
    val profileUIEvent = _profileEvent.consumeAsFlow()

    fun getFullInfo(){
        _profileEvent.trySend(ProfileUIEvent.Loading)
        getFullInfoUseCase.invoke().onEach {
            _profileEvent.trySend(ProfileUIEvent.Success(it))
        }.catch {
            errorHandler.handleError(it)
            _profileEvent.trySend(ProfileUIEvent.Error(it.message))
        }.launchIn(viewModelScope)
    }
}


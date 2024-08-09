package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val fullInfoUseCase: GetFullInfoUseCase
) : BaseViewModel() {
    private val _getUserData = Channel<UpdateProfileEvent>()
    val getUserData = _getUserData.consumeAsFlow()
    fun getUserData() {
        _getUserData.trySend(UpdateProfileEvent.Loading)
        fullInfoUseCase().onEach {
            fullInfoUseCase.invoke()
        }.catch {
            errorHandler.handleError(it)
            _getUserData.trySend(UpdateProfileEvent.Error(it.message))
        }.launchIn(viewModelScope)
    }
}
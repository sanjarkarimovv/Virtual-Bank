package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val errorHandler: ErrorHandler
    ): BaseViewModel() {
        private val _fullInfoEvent= Channel<FullInfoUIModel>()
    val fullInfoEvent = _fullInfoEvent.consumeAsFlow()

    fun getFullInfo() {
        getFullInfoUseCase().onEach {

            _fullInfoEvent.trySend(it)

        }.catch {
            errorHandler.handleError(it)
            //
        }.launchIn(viewModelScope)
    }
}
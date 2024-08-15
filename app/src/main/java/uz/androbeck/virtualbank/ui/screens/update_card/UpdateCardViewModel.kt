package uz.androbeck.virtualbank.ui.screens.update_card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.card.UpdateCardUIModel
import uz.androbeck.virtualbank.domain.useCases.card.PutUpdateCardUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.utils.extentions.share
import javax.inject.Inject

@HiltViewModel
class UpdateCardViewModel @Inject constructor(
    private val updateCardUseCase: PutUpdateCardUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    private val _isSuccessfullyEvent = MutableLiveData(false)
    val isSuccessfullyEvent: LiveData<Boolean> get() = _isSuccessfullyEvent

    fun updateCard(updateCardUIModel: UpdateCardUIModel) {
        val reqModel = UpdateCardUIModel(
            id = updateCardUIModel.id,
            name = updateCardUIModel.name,
            themeType = updateCardUIModel.themeType,
            isVisible = updateCardUIModel.isVisible
        )
        updateCardUseCase(reqModel).onEach {
            _isSuccessfullyEvent.value = true
        }.catch {
            errorHandler.handleError(it)
        }.launchIn(viewModelScope)
    }
}
package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanResUIModel
import uz.androbeck.virtualbank.domain.useCases.card.GetCardsUseCase
import uz.androbeck.virtualbank.domain.useCases.transfer.GetCardOwnerByPanUseCase
import uz.androbeck.virtualbank.domain.useCases.transfer.TransferUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class TransferFragmentViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val transferUseCase: TransferUseCase,
    private val getCardOwnerByPanUseCase: GetCardOwnerByPanUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    private val _cardsResponse = MutableLiveData<List<CardUIModel>>()
    val cardsResponse: LiveData<List<CardUIModel>> = _cardsResponse

    private val _cardOwnerResponse = MutableLiveData(false)
    val cardOwnerResponse: LiveData<Boolean> = _cardOwnerResponse

    private val _isErrorEvent = MutableLiveData(false)
    val isErrorEvent: LiveData<Boolean> get() = _isErrorEvent

    private val _isGetCardOwnerByPanEvent = MutableLiveData<GetCardOwnerByPanResUIModel>()
    val isGetCardOwnerByPanEvent: LiveData<GetCardOwnerByPanResUIModel> get() = _isGetCardOwnerByPanEvent

    init {
        getCards()
    }

    private fun getCards() {
        viewModelScope.launch {
            getCardsUseCase.getCardsFromNetwork().onEach {
                _cardsResponse.value = it
            }.launchIn(this)
        }
    }

    fun getCardOwnerByPan(reqUIModel: GetCardOwnerByPanReqUIModel) {
        val reqModel = GetCardOwnerByPanReqUIModel(
            pan = reqUIModel.pan
        )
        getCardOwnerByPanUseCase(reqModel).onEach {
            _cardOwnerResponse.value = true
            _isGetCardOwnerByPanEvent.value = it
        }.catch { th ->
            _isErrorEvent.value = true
            errorHandler.handleError(th)
        }.launchIn(viewModelScope)
    }

}
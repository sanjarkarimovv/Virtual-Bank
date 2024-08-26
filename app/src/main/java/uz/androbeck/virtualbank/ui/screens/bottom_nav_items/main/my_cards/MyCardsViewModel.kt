package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.domain.useCases.card.GetCardsUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.utils.extentions.share
import javax.inject.Inject

@HiltViewModel
class MyCardsViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {

    private val _getCardsEvent = MutableStateFlow<MyCardsUIEvent>(MyCardsUIEvent.Loading)
    val getCardsEvent = _getCardsEvent.asStateFlow()

    fun getCards() = viewModelScope.launch(Dispatchers.IO) {

        getCardsUseCase.getCardsFromNetwork().onEach { getCardsFromNetwork ->
            getCardsUseCase.getCardsFromCache().forEach { getCardsFromCache ->
                if (getCardsFromCache.pan == null) return@forEach
                val matchingCardPan = getCardsFromNetwork.find {
                    it.pan == getCardsFromCache.pan?.takeLast(4)
                }
                if (matchingCardPan != null) {
                    getCardsFromNetwork.forEach { it.pan = matchingCardPan.pan }
                }
            }
            viewModelScope.launch {
                _getCardsEvent.value = (MyCardsUIEvent.Success(getCardsFromNetwork))
            }
        }.catch {
            errorHandler.handleError(it)
            _getCardsEvent.value = (MyCardsUIEvent.Error(it.message))
        }.launchIn(this)
    }

    init {
        getCards()
    }
}
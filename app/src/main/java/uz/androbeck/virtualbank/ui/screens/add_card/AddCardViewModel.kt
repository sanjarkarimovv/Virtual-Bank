package uz.androbeck.virtualbank.ui.screens.add_card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.card.AddCardReqUIModel
import uz.androbeck.virtualbank.domain.useCases.card.AddCardUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val addCardUseCase: AddCardUseCase,
    private val errorHandler: ErrorHandler,
) : BaseViewModel() {

    private val _isAddCardEvent = MutableLiveData(false)
    val isAddCard: LiveData<Boolean> get() = _isAddCardEvent

    private val _isErrorEvent = MutableLiveData(false)
    val isErrorEvent: LiveData<Boolean> get() = _isErrorEvent

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> get() = _isFormValid.asStateFlow()

    fun validateFields(cardNumber: String, validityPeriod: String, cardName: String) {
        val cardNumberLength = cardNumber.replace(" ", "").length
        val validityPeriodLength = validityPeriod.length
        val cardNameLength = cardName.replace(" ", "").trim().length
        _isFormValid.value =
            cardNumberLength == 16 && validityPeriodLength == 5 && cardNameLength >= 5
    }

    fun resultCardNumber(inputString: String): String {
        return inputString.replace(" ", "")
    }

    fun extractDate(inputString: String): Pair<String, String> {
        val (month, year) = inputString.split("/")
        val formattedMonth = month.toInt().toString()
        val fullYear = "20$year"
        return formattedMonth to fullYear
    }

    fun addCard(requestModel: AddCardReqUIModel) {
        val reqModel = AddCardReqUIModel(
            pan = requestModel.pan,
            expiredMonth = requestModel.expiredMonth,
            expiredYear = requestModel.expiredYear,
            name = requestModel.name
        )
        addCardUseCase(reqModel).onEach {
            _isAddCardEvent.value = true
        }.catch { th ->
            _isErrorEvent.value = true
            errorHandler.handleError(th)
        }.launchIn(viewModelScope)
    }
}

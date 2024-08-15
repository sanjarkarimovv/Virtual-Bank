package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.add_card

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor() : BaseViewModel() {

    private val _isFormValid = MutableStateFlow(false)
    val isFormValid: StateFlow<Boolean> get() = _isFormValid.asStateFlow()

    fun validateFields(cardNumber: String, validityPeriod: String) {
        val cardNumberLength = cardNumber.replace(" ", "").length
        val validityPeriodLength = validityPeriod.length
        _isFormValid.value = cardNumberLength == 16 && validityPeriodLength == 5 && cardNumber.isNotEmpty()
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
}

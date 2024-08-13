package uz.androbeck.virtualbank.ui.screens.pin_code.confirmpincode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.screens.pin_code.events.ConfirmPinCodeEvent
import uz.androbeck.virtualbank.ui.screens.pin_code.usecases.ConfirmPinCodeUseCase
import uz.androbeck.virtualbank.ui.screens.pin_code.usecases.RegisterPinUseCase
import javax.inject.Inject

@HiltViewModel
class ConfirmPinCodeViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider,
    private val registerPinUseCase: RegisterPinUseCase,
    private val confirmPinCodeUseCase: ConfirmPinCodeUseCase
) : ViewModel() {

    private val _pinCodeList = MutableLiveData<MutableList<String>>(mutableListOf())
    val pinCodeList: LiveData<MutableList<String>> get() = _pinCodeList

    private val _confirmPinCodeEvent = MutableLiveData<ConfirmPinCodeEvent>()
    val confirmPinCodeEvent: LiveData<ConfirmPinCodeEvent> get() = _confirmPinCodeEvent

    fun addDigit(digit: String) {
        _pinCodeList.value?.let {
            if (it.size < 4) {
                it.add(digit)
                _pinCodeList.value = it
            }
        }
    }

    fun removeLastDigit() {
        _pinCodeList.value?.let {
            if (it.isNotEmpty()) {
                it.removeLast()
                _pinCodeList.value = it
            }
        }
    }

    fun clearPinCode() {
        _pinCodeList.value?.clear()
        _pinCodeList.value = _pinCodeList.value
    }

    fun handlePinCodeCompletion() {
        viewModelScope.launch {
            val pinCode = _pinCodeList.value.toString()

            if (confirmPinCodeUseCase.confirmPinCode(pinCode)) {
                _confirmPinCodeEvent.value = ConfirmPinCodeEvent.PinValidated
                registerPinUseCase.registerPin(pinCode)
            } else {
                _confirmPinCodeEvent.value = ConfirmPinCodeEvent.PinValidationFailed
            }
        }
    }

    fun clearReservePinCode() {
        prefsProvider.pinCodeReserve = ""
    }

    fun setBiometrics(cond: Boolean) {
        prefsProvider.useBiometric = cond
    }
}
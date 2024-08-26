package uz.androbeck.virtualbank.ui.screens.pin_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.ui.screens.pin_code.events.PinCodeEvent
import uz.androbeck.virtualbank.ui.screens.pin_code.usecases.RegisterReservePinUseCase
import uz.androbeck.virtualbank.ui.screens.pin_code.usecases.ValidatePinUseCase
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider,
    private val registerReservePinUseCase: RegisterReservePinUseCase,
    private val validatePinUseCase: ValidatePinUseCase
) : BaseViewModel() {
    private val _pinCodeList = MutableLiveData<MutableList<String>>(mutableListOf())
    val pinCodeList: LiveData<MutableList<String>> get() = _pinCodeList

    private val _fromRegister = MutableLiveData<Boolean>()
    val fromRegister: LiveData<Boolean> get() = _fromRegister

    private val _pinCodeEvent = MutableLiveData<PinCodeEvent>()
    val pinCodeEvent: LiveData<PinCodeEvent> get() = _pinCodeEvent

    private val _errorAttempts = MutableLiveData<Int>()
    val errorAttempts: LiveData<Int> get() = _errorAttempts

    private val _errorLogout = MutableLiveData<Boolean>()
    val errorLogout: LiveData<Boolean> get() = _errorLogout

    init {
        userLog()
        _errorAttempts.value = prefsProvider.errorAttempts
    }

    private fun incrementErrorAttempts() {
        val attempts = (_errorAttempts.value ?: 0) + 1
        _errorAttempts.value = attempts
        prefsProvider.errorAttempts = attempts
        if (attempts >= 5) {
            _errorLogout.value = true
            handlePinCodeExit()
        }
    }

    fun resetErrorAttempts() {
        _errorAttempts.postValue(0)
        prefsProvider.errorAttempts = 0
    }

    private fun userLog() {
        viewModelScope.launch {
            val fromRegister = prefsProvider.pinCode.isEmpty()
            _fromRegister.postValue(!fromRegister)
        }
    }


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

            if (prefsProvider.pinCode.isEmpty()) {
                registerReservePinUseCase.registerReservePin(pinCode)
                _pinCodeEvent.value = PinCodeEvent.PinRegistered
            } else {
                if (validatePinUseCase.isValidPin(pinCode)) {
                    _pinCodeEvent.value = PinCodeEvent.PinValidated
                    delay(2000L)
                    resetErrorAttempts()
                } else {
                    _pinCodeEvent.value = PinCodeEvent.PinValidationFailed
                    _pinCodeList.value?.clear()
                    delay(2000L)
                    incrementErrorAttempts()
                }
            }
        }
    }

    fun handlePinCodeExit() {
        prefsProvider.clear()
        resetErrorAttempts()
    }

    fun checkBiometrics(): Boolean {
        return prefsProvider.useBiometric
    }
}

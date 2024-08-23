package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.screens.change_pin_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.enums.PinStep
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events.ChangePinAnimEvent
import uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.events.ChangePinCodeEvent
import javax.inject.Inject

@HiltViewModel
class ChangePinCodeViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider
) : ViewModel() {

    private val _pinCodeList = MutableLiveData<MutableList<String>>(mutableListOf())
    val pinCodeList: LiveData<MutableList<String>> get() = _pinCodeList

    private val _errorAttempts = MutableLiveData<Int>()
    val errorAttempts: LiveData<Int> get() = _errorAttempts

    private val _errorLogout = MutableLiveData<Boolean>()
    val errorLogout: LiveData<Boolean> get() = _errorLogout

    private val _pinCodeProgress = MutableLiveData<ChangePinCodeEvent>()
    val pinCodeProgress: LiveData<ChangePinCodeEvent> get() = _pinCodeProgress

    private val _errorAnim = MutableLiveData<ChangePinAnimEvent>()
    val errorAnim: LiveData<ChangePinAnimEvent> get() = _errorAnim

    private val _pinCodeSame = MutableLiveData<Boolean>()
    val pinCodeSame: LiveData<Boolean> get() = _pinCodeSame

    private var currentStep = PinStep.CHECK_CURRENT_PIN
    private var currentPin = ""

    init {
        _pinCodeProgress.value = ChangePinCodeEvent.PinCheck
    }

    fun addDigit(digit: String) {
        _pinCodeList.value?.let {
            if (it.size < 4) {
                it.add(digit)
                _pinCodeList.value = it
            }
            if (it.size == 4) {
                handlePinCodeCompletion()
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

    fun confirmPinValidation() {
        if (currentStep == PinStep.VALIDATE_NEW_PIN) {
            validateNewPin()
        }
    }

    private fun incrementErrorAttempts() {
        val attempts = (_errorAttempts.value ?: 0) + 1
        _errorAttempts.value = attempts
        if (attempts >= 5) {
            _errorLogout.value = true
            handlePinCodeExit()
        }
    }

    private fun resetErrorAttempts() {
        _errorAttempts.value = 0
        prefsProvider.errorAttempts = 0
    }

    private fun handlePinCodeExit() {
        prefsProvider.clear()
        resetErrorAttempts()
    }

    fun handlePinCodeCompletion() {
        when (currentStep) {
            PinStep.CHECK_CURRENT_PIN -> validateCurrentPin()
            PinStep.SET_NEW_PIN -> setNewPin()
            PinStep.VALIDATE_NEW_PIN -> {
                _pinCodeProgress.value = ChangePinCodeEvent.PinValidate
            }
        }
    }

    private fun validateCurrentPin() {
        val pinCode = _pinCodeList.value.toString()
        if (pinCode == prefsProvider.pinCode) {
            _errorAnim.value = ChangePinAnimEvent.PinValidated
            viewModelScope.launch {
                delay(900L)
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
                currentStep = PinStep.SET_NEW_PIN
                clearPinCode()
                _pinCodeProgress.value = ChangePinCodeEvent.PinSet
            }

        } else {
            _errorAnim.value = ChangePinAnimEvent.PinNotValidated
            viewModelScope.launch {
                delay(2000L)
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
            }
            incrementErrorAttempts()
        }
    }

    private fun setNewPin() {
        currentPin = _pinCodeList.value.toString()
        if (currentPin != prefsProvider.pinCode) {
            _errorAnim.value = ChangePinAnimEvent.PinValidated
            viewModelScope.launch {
                delay(900L)
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
                currentStep = PinStep.VALIDATE_NEW_PIN
                clearPinCode()
                _pinCodeProgress.value = ChangePinCodeEvent.PinValidate
            }
        } else {
            _errorAnim.value = ChangePinAnimEvent.PinNotValidated
            viewModelScope.launch {
                delay(2000L)
                _pinCodeSame.value = true
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
            }
        }

    }

    private fun validateNewPin() {
        val enteredPin = _pinCodeList.value.toString()
        if (enteredPin == currentPin) {
            _errorAnim.value = ChangePinAnimEvent.PinValidated
            viewModelScope.launch {
                delay(900L)
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
                _pinCodeProgress.value = ChangePinCodeEvent.PinSuccess
                prefsProvider.pinCode = enteredPin
                prefsProvider.pinCodeReserve = enteredPin
            }
        } else {
            _errorAnim.value = ChangePinAnimEvent.PinNotValidated
            viewModelScope.launch {
                delay(2000L)
                _errorAnim.value = ChangePinAnimEvent.PinNeutral
            }
        }
    }
}
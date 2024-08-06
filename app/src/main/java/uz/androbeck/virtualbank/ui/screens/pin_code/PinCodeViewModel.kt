package uz.androbeck.virtualbank.ui.screens.pin_code

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class PinCodeViewModel @Inject constructor(
    private val prefsProvider: PreferencesProvider,
    private val registerPinUseCase: RegisterPinUseCase,
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

    private val _timerRunning = MutableLiveData<Boolean>()
    val timerRunning: LiveData<Boolean> get() = _timerRunning

    init {
        _errorAttempts.value = prefsProvider.errorAttempts
        checkTimer()
    }

    private fun checkTimer() {
        val lastErrorTime = prefsProvider.lastErrorTimestamp
        if (System.currentTimeMillis() - lastErrorTime < 60000) {
            startTimer(60000 - (System.currentTimeMillis() - lastErrorTime))
        }
    }

    private fun startTimer(duration: Long) {
        _timerRunning.value = true
        Handler(Looper.getMainLooper()).postDelayed({
            _timerRunning.value = false
            resetErrorAttempts()
        }, duration)
    }

    private fun incrementErrorAttempts() {
        val attempts = (_errorAttempts.value ?: 0) + 1
        _errorAttempts.value = attempts
        prefsProvider.errorAttempts = attempts
        if (attempts >= 5) {
            prefsProvider.lastErrorTimestamp = System.currentTimeMillis()
            startTimer(60000)
        }
    }

    private fun resetErrorAttempts() {
        _errorAttempts.value = 0
        prefsProvider.errorAttempts = 0
    }

    fun userLog() {
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

                if (it.size == 4 && _fromRegister.value == true) {
                    handlePinCodeCompletion()
                }
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
        if (_timerRunning.value == true) {
            // Handle timer running scenario, e.g., show a message to the user.
            return
        }
        viewModelScope.launch {
            val pinCode = _pinCodeList.value.toString()

            if (prefsProvider.pinCode.isEmpty()) {
                registerPinUseCase.registerPin(pinCode)
                _pinCodeEvent.value = PinCodeEvent.PinRegistered
            } else {
                if (validatePinUseCase.isValidPin(pinCode)) {
                    _pinCodeEvent.value = PinCodeEvent.PinValidated
                    Handler(Looper.getMainLooper()).postDelayed({ resetErrorAttempts() }, 2000L)
                } else {
                    _pinCodeEvent.value = PinCodeEvent.PinValidationFailed
                    _pinCodeList.value?.clear()
                    Handler(Looper.getMainLooper()).postDelayed({ incrementErrorAttempts() }, 2000L)
                }
            }
        }
    }

    fun handlePinCodeExit() {
        prefsProvider.token = ""
        prefsProvider.pinCode = ""
        resetErrorAttempts()
    }
}

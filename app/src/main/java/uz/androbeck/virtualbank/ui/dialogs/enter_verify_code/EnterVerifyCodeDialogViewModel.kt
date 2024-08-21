package uz.androbeck.virtualbank.ui.dialogs.enter_verify_code

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import uz.androbeck.virtualbank.domain.useCases.authentication.AuthVerifyUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.screens.Screen
import uz.androbeck.virtualbank.utils.Constants.Number.reENTRY_GET_CODE_TIME
import javax.inject.Inject

@HiltViewModel
class EnterVerifyCodeDialogViewModel @Inject constructor(
    private val authVerifyUseCase: AuthVerifyUseCase,
    private val preferencesProvider: PreferencesProvider,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _authVerifyEvent = MutableStateFlow(false)
    val authVerifyEvent = _authVerifyEvent.asStateFlow()

    private val _timerTextEvent = MutableLiveData<String>()
    val timerTextEvent: LiveData<String> get() = _timerTextEvent

    private val _timerFinishedEvent = MutableLiveData(false)
    val timerFinishedEvent: LiveData<Boolean> get() = _timerFinishedEvent

    private val _editTextValuesEvent = MutableLiveData<List<String>>()
    val editTextValuesEvent: LiveData<List<String>> get() = _editTextValuesEvent

    private val _allFieldsFilledEvent = MutableLiveData(false)
    val allFieldsFilledEvent: LiveData<Boolean> get() = _allFieldsFilledEvent

    private val _isErrorEvent = MutableLiveData(false)
    val isErrorEvent: LiveData<Boolean> get() = _isErrorEvent

    private var countDownTimer: CountDownTimer? = null

    init {
        _editTextValuesEvent.value = List(6) { "" }
        _allFieldsFilledEvent.value = false
        startTimer()
    }

    fun authVerify(code: String?, token: String?, screen: Screen?) {
        code?.let {
            if (token == null) {
                return
            }
            val requestUIModel = CodeVerifyReqUIModel(
                token = token,
                code = it
            )
            if (screen == null) {
                return
            }
            authVerifyUseCase(
                screen,
                requestUIModel
            ).onEach { uiModel ->
                preferencesProvider.accessToken = uiModel.accessToken.orEmpty()
                preferencesProvider.refreshToken = uiModel.refreshToken.orEmpty()
                _authVerifyEvent.value = true
            }.catch { th ->
                errorHandler.handleError(th)
                _isErrorEvent.value = true
            }.launchIn(viewModelScope)
        }
    }

    private fun startTimer(durationInMillis: Long = reENTRY_GET_CODE_TIME) {
        countDownTimer = object : CountDownTimer(durationInMillis, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                _timerTextEvent.value = String.format("%d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                _timerFinishedEvent.value = true
            }
        }.start()
    }

    fun updateEditTextValue(index: Int, value: String) {
        _editTextValuesEvent.value = _editTextValuesEvent.value?.toMutableList()?.apply {
            this[index] = value
        }
        checkAllFieldsFilled()
    }

    private fun checkAllFieldsFilled() {
        _allFieldsFilledEvent.value = _editTextValuesEvent.value?.all { it.isNotEmpty() } == true
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}
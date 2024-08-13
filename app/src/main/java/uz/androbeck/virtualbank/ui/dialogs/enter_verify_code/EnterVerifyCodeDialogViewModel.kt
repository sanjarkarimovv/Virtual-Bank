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
import javax.inject.Inject

@HiltViewModel
class EnterVerifyCodeDialogViewModel @Inject constructor(
    private val authVerifyUseCase: AuthVerifyUseCase,
    private val preferencesProvider: PreferencesProvider,
    private val errorHandler: ErrorHandler,
) : ViewModel() {

    private val _signUpVerifyEvent = MutableStateFlow(false)
    val signUpVerifyEvent = _signUpVerifyEvent.asStateFlow()

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String> get() = _timerText

    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished: LiveData<Boolean> get() = _timerFinished

    private val _editTextValues = MutableLiveData<List<String>>()
    val editTextValues: LiveData<List<String>> get() = _editTextValues

    private val _allFieldsFilled = MutableLiveData<Boolean>()
    val allFieldsFilled: LiveData<Boolean> get() = _allFieldsFilled

    private val _isError = MutableLiveData(false)
    val isError: LiveData<Boolean> get() = _isError

    private var countDownTimer: CountDownTimer? = null

    init {
        _editTextValues.value = List(6) { "" }
        _allFieldsFilled.value = false
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
                preferencesProvider.token = uiModel.accessToken.orEmpty()
                preferencesProvider.refreshToken = uiModel.refreshToken.orEmpty()
                _signUpVerifyEvent.value = true
            }.catch { th ->
                errorHandler.handleError(th)
                _isError.value = true
            }.launchIn(viewModelScope)
        }
    }

    private fun startTimer(durationInMillis: Long = rENTER_GET_CODE_TIME) {
        countDownTimer = object : CountDownTimer(durationInMillis, 1000) {
            @SuppressLint("DefaultLocale")
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                _timerText.postValue(String.format("%d:%02d", minutes, seconds))
            }

            override fun onFinish() {
                _timerFinished.postValue(true)
            }
        }.start()
    }

    fun updateEditTextValue(index: Int, value: String) {
        _editTextValues.value = _editTextValues.value?.toMutableList()?.apply {
            this[index] = value
        }
        checkAllFieldsFilled()
    }

    private fun checkAllFieldsFilled() {
        _allFieldsFilled.value = _editTextValues.value?.all { it.isNotEmpty() } == true
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }
}

const val rENTER_GET_CODE_TIME = 180000L

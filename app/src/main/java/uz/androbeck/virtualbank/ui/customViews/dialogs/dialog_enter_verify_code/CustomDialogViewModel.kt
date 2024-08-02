package uz.androbeck.virtualbank.ui.customViews.dialogs.dialog_enter_verify_code

import android.annotation.SuppressLint
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CustomDialogViewModel : ViewModel() {

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String> get() = _timerText

    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished: LiveData<Boolean> get() = _timerFinished

    private val _editTextValues = MutableLiveData<List<String>>()
    val editTextValues: LiveData<List<String>> get() = _editTextValues

    private val _allFieldsFilled = MutableLiveData<Boolean>()
    val allFieldsFilled: LiveData<Boolean> get() = _allFieldsFilled

    private var countDownTimer: CountDownTimer? = null

    init {
        _editTextValues.value = List(6) { "" }
        _allFieldsFilled.value = false
        startTimer()
    }

    private fun startTimer(durationInMillis: Long = 30000) {
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

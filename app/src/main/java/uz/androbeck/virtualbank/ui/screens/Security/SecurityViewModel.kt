package uz.androbeck.virtualbank.ui.screens.Security

import android.annotation.SuppressLint
import android.content.Context
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val provider: PreferencesProvider,
) : ViewModel() {


    fun biometricLogin(isCheck: Boolean) {
        provider.biometricLogin = isCheck
    }

    fun seekBarResult(seekBar: SeekBar, textView: TextView, context: Context) {
        seekBar.max = 60
        seekBar.progress = provider.autoBlockTime.toInt()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("CommitPrefEdits")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
                provider.autoBlockTime = progress.toLong()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                context.toast("Time changed")
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        textView.text = provider.autoBlockTime.toString()
    }

    fun paymentConfirmation(isCheck: Boolean) {
        provider.paymentConfirmation = isCheck
    }

    fun autoBlockIsOn(isCheck: Boolean) {
        provider.autoBlockIsOn = isCheck
    }
}
package uz.androbeck.virtualbank.ui.screens.Security

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.view.View
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.screens.Security.service.LocationService
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.invisible
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




    fun startLocation(context: Context) {
        val serviceIntent = Intent(context, LocationService::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent)
        } else {
            context.startService(serviceIntent)
        }
    }

    fun stopLocation(context: Context) {
        val serviceIntent = Intent(context, LocationService::class.java)
        context.stopService(serviceIntent)
    }
}
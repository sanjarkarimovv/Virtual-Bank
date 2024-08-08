package uz.androbeck.virtualbank.ui.screens.pin_code

import android.content.Context
import androidx.biometric.BiometricManager

object BiometricUtils {
    fun isBiometricReady(context : Context) : Boolean{
        val biometricManager = BiometricManager.from(context)
        return when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }
}
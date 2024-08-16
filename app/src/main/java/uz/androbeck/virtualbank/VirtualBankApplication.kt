package uz.androbeck.virtualbank

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import uz.androbeck.virtualbank.databinding.FragmentProfileBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Inject

//Hello Virtual Bank
@HiltAndroidApp
class VirtualBankApplication : Application() {
    @Inject
    lateinit var preferencesProvider: PreferencesProvider
    lateinit var binding: FragmentProfileBinding
    override fun onCreate() {
        super.onCreate()

        val themeCode = preferencesProvider.theme
        val nightMode = when (themeCode) {
            Constants.Theme.LIGHT-> AppCompatDelegate.MODE_NIGHT_NO
            Constants.Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}
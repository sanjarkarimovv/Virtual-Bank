package uz.androbeck.virtualbank

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import dagger.hilt.android.HiltAndroidApp
import uz.androbeck.virtualbank.data.db.AppDatabase
import uz.androbeck.virtualbank.data.db.entity.CardInfoEntity
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import java.util.Locale
import javax.inject.Inject

//Hello Virtual Bank
@HiltAndroidApp
class VirtualBankApplication : Application() {






    override fun onCreate() {
        super.onCreate()

       // println("AAAAAA ::::: $newCard")


    }

}
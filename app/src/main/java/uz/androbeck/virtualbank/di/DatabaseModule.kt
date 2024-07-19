package uz.androbeck.virtualbank.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return try {
            val masterKeyAlias =
                MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
            EncryptedSharedPreferences.create(
                context,
                Constants.FileName.SHARED_PREFS,
                masterKeyAlias,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (ignore: Exception) {
            Log.e("shared prefs exception", ignore.message ?: ignore.localizedMessage)
            context.getSharedPreferences(Constants.FileName.SHARED_PREFS, Context.MODE_PRIVATE)
        }
    }

    @Singleton
    @Provides
    fun providePreferencesProvider(sharedPreferences: SharedPreferences) =
        PreferencesProvider(sharedPreferences)
}
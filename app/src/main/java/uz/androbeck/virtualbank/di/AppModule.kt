package uz.androbeck.virtualbank.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.ui.resource.ResProvider
import uz.androbeck.virtualbank.ui.resource.ResProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResource(
        @ApplicationContext context: Context
    ) : ResProvider = ResProviderImpl(context)

}
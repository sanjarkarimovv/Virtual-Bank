package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpClintModule {

    @Provides
    @Singleton
    fun provideOkHttpClint(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}
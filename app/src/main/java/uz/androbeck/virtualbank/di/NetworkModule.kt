package uz.androbeck.virtualbank.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter 
import retrofit2.Retrofit
import uz.androbeck.virtualbank.BuildConfig
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.network.ErrorHandlingCallAdapterFactory
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.network.errors.ErrorHandlerImpl
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun provideConverter(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideChuckerCollector(context: Context) = ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR
    )

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        context: Context,
        collector: ChuckerCollector
    ) = ChuckerInterceptor.Builder(context)
        .collector(collector)
        .maxContentLength(250_000L)
        .redactHeaders(Constants.Header.TOKEN_TITLE, Constants.Header.TOKEN_TYPE)
        .alwaysReadResponseBody(true)
        .createShortcut(true)
        .build()

    @[Provides Singleton]
    fun provideJsonSerializer(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = false
        }
    }

    @[Provides Singleton]
    fun provideOkHttpClient(
        prefsProvider: PreferencesProvider,
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder().apply {
                    if (prefsProvider.token.isNotEmpty()) {
                        addHeader(
                            Constants.Header.TOKEN_TITLE,
                            Constants.Header.TOKEN_TYPE + " " + prefsProvider.token
                        )
                    }
                    addHeader(
                        Constants.Header.ACCEPT_TITLE,
                        Constants.Header.APPLICATION_JSON_VALUE
                    )
                }
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Log.d("OkHttp", message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory,
        callFactory: ErrorHandlingCallAdapterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .addCallAdapterFactory(callFactory)
            .build()
    }

    @Provides

    @Singleton
    fun provideAuthService(
        retrofit: Retrofit,
    ): AuthenticationService = retrofit.create(AuthenticationService::class.java)

    @Provides
    @Singleton
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeService = retrofit.create(HomeService::class.java)

    @Provides
    fun provideErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler {
        return errorHandlerImpl
    }
}
package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.SignUpVerifyService
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote.SignUpVerifyRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote.SignUpVerifyRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Singleton
    @Provides
    fun provideAuthenticationRemoteDataSource(
        service: AuthenticationService
    ): AuthenticationRemoteDataSource {
        return AuthenticationRemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideSignUpVerifyRemoteDataSource(
        service: SignUpVerifyService
    ): SignUpVerifyRemoteDataSource {
        return SignUpVerifyRemoteDataSourceImpl(service)
    }
}
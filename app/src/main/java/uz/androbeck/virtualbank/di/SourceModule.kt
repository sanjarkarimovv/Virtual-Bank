package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.MainService
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.MainRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.MainRemoteDataSourceImpl
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
    fun provideMainRemoteDataSource(
        service: MainService
    ): MainRemoteDataSource {
        return MainRemoteDataSourceImpl(service)
    }

}
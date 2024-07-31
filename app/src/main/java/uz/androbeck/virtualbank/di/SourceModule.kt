package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.FullInfoService
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.FullInfoRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.FullInfoRemoteDataSourceImpl
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
        service: FullInfoService
    ): FullInfoRemoteDataSource {
        return FullInfoRemoteDataSourceImpl(service)
    }

}
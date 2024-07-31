package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.repository.authenticationRepository.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.authenticationRepository.AuthenticationRepositoryImpl
import uz.androbeck.virtualbank.data.repository.mainRepository.MainRepository
import uz.androbeck.virtualbank.data.repository.mainRepository.MainRepositoryImpl
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.MainRemoteDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        authenticationRemoteDataSource: AuthenticationRemoteDataSource
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        mainRemoteDataSource: MainRemoteDataSource
    ):MainRepository{
        return MainRepositoryImpl(mainRemoteDataSource)
    }

}
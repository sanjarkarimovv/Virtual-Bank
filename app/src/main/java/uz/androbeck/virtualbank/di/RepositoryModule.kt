package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepositoryImpl
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.data.repository.history.HistoryRepositoryImpl
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.data.repository.home.HomeRepositoryImpl
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryPagingSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSource
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
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource
    ):HomeRepository{
        return HomeRepositoryImpl(homeRemoteDataSource)
    }
    @Provides
    @Singleton
    fun provideHistoryRepository(
        historyRemoteDatasource: HistoryRemoteDatasource,
        historyPagingSource: HistoryPagingSource
    ):HistoryRepository{
        return HistoryRepositoryImpl(historyPagingSource,historyRemoteDatasource)
    }

}
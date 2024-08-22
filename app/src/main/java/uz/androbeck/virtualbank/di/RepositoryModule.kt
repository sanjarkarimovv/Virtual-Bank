package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepositoryImpl
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.data.repository.card.CardRepositoryImpl
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.data.repository.history.HistoryRepositoryImpl
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.data.repository.home.HomeRepositoryImpl
import uz.androbeck.virtualbank.data.source.local.home.HomeLocalDatasource
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepositoryImpl
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryPagingSource
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.transfer.TransferRemoteDataSource
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
        homeRemoteDataSource: HomeRemoteDataSource,
        homeLocalDatasource: HomeLocalDatasource
    ):HomeRepository{
        return HomeRepositoryImpl(homeRemoteDataSource,homeLocalDatasource)
    }

    @Provides
    @Singleton
    fun provideCardRepository(
        cardRemoteDataSource: CardRemoteDataSource
    ):CardRepository {
        return CardRepositoryImpl(cardRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideHistoryRepository(
        historyRemoteDatasource: HistoryRemoteDatasource,
        historyPagingSource: HistoryPagingSource
    ):HistoryRepository{
        return HistoryRepositoryImpl(historyPagingSource,historyRemoteDatasource)
    }

    @Provides
    @Singleton
    fun provideTransferRepository(
       transferRemoteDataSource: TransferRemoteDataSource
    ): TransferRepository {
        return TransferRepositoryImpl(transferRemoteDataSource)
    }
}
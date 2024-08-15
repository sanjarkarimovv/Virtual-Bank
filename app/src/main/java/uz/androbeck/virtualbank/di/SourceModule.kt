package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.CardService
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.db.dao.HomeDao
import uz.androbeck.virtualbank.data.source.local.home.HomeLocalDatasource
import uz.androbeck.virtualbank.data.source.local.home.HomeLocalDatasourceImpl
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDatasource
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSourceImpl
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
        service: HomeService
    ): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideHistoryRemoteDataSource(
        service: HistoryService
    ): HistoryRemoteDatasource {
        return HistoryRemoteDataSourceImpl(service)
    }
    @Singleton
    @Provides
    fun provideCardRemoteDataSource(
        service: CardService
    ): CardRemoteDataSource {
        return CardRemoteDataSourceImpl(service)
    }

    @Singleton
    @Provides
    fun provideHomeLocalDataSource(
        dao: HomeDao
    ): HomeLocalDatasource {
        return HomeLocalDatasourceImpl(dao)
    }

}
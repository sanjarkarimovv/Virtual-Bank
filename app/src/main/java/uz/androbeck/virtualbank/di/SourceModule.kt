package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.api.CardsService
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSourceImpl
import uz.androbeck.virtualbank.data.source.remote.cards.CardsDataSource
import uz.androbeck.virtualbank.data.source.remote.cards.CardsDataSourceImpl
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
    ): AuthenticationRemoteDataSource = AuthenticationRemoteDataSourceImpl(service)


    @Singleton
    @Provides
    fun provideMainRemoteDataSource(
        service: HomeService
    ): HomeRemoteDataSource = HomeRemoteDataSourceImpl(service)

    @Singleton
    @Provides
    fun provideHistoryRemoteDataSource(
        service: HistoryService
    ): HistoryRemoteDatasource = HistoryRemoteDataSourceImpl(service)


    @[Provides Singleton]
    fun provideCardsDataSource(
        service: CardsService
    ): CardsDataSource = CardsDataSourceImpl(service)

}
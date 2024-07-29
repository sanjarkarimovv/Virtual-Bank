package uz.androbeck.virtualbank.data.repository

import uz.androbeck.virtualbank.data.local.source.LocalDataSource
import uz.androbeck.virtualbank.data.remote.source.RemoteDataSource

class DataSourceRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSourceRepository {

}
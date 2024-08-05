package uz.androbeck.virtualbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.source.remote.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    ) : HomeRepository {
    override fun getFullInfo(): Flow<FullInfoDto> = homeRemoteDataSource.getFullInfo()
}
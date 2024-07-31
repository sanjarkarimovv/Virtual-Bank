package uz.androbeck.virtualbank.data.repository.mainRepository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.source.remote.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,

    ) : HomeRepository {
    override fun getFullInfo(token: String?): Flow<FullInfoDto> =
        homeRemoteDataSource.getFullInfo(token)

}
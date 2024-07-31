package uz.androbeck.virtualbank.data.repository.mainRepository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.source.remote.FullInfoRemoteDataSource
import javax.inject.Inject

class FullInfoRepositoryImpl @Inject constructor(
    private val fullInfoRemoteDataSource: FullInfoRemoteDataSource,

    ) : FullInfoRepository {
    override fun getFullInfo(token: String?): Flow<FullInfoDto> =
        fullInfoRemoteDataSource.getFullInfo(token)

}
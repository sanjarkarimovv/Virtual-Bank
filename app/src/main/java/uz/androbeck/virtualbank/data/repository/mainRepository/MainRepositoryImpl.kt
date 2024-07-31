package uz.androbeck.virtualbank.data.repository.mainRepository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.data.source.remote.MainRemoteDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
private val mainRemoteDataSource: MainRemoteDataSource,

) : MainRepository {
    override fun getFullInfo(): Flow<MainFullInfoDto> {
        return mainRemoteDataSource.getFullInfo()
    }
}
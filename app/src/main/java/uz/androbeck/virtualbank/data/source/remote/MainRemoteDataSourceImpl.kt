package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.MainService
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val mainService: MainService
) : MainRemoteDataSource {
    override fun getFullInfo(token:String?): Flow<MainFullInfoDto>{
        return flow {
            emit(mainService.getFullInfo(token))
        }
    }
}
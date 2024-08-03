package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.UpdateInfoReqDto
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val fullInfoService: HomeService
) : HomeRemoteDataSource {


    override fun getFullInfo(): Flow<FullInfoDto> = flow {
            emit(fullInfoService.getFullInfo())
        }

    override fun putUpdateInfo(request: UpdateInfoReqDto): Flow<MessageResDto> = flow {
        emit(fullInfoService.putUpdateInfo(request))

    }

}
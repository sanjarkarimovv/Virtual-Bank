package uz.androbeck.virtualbank.data.source.remote.home

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val fullInfoService: HomeService
) : HomeRemoteDataSource {

    override fun getFullInfo() = flow {
        emit(fullInfoService.getFullInfo())
    }

    override fun getBasicInfo() = flow {
        emit(fullInfoService.getBasicInfo())
    }

    override fun putUpdateInfo(request: UpdateInfoReqDto) = flow {
        emit(fullInfoService.putUpdateInfo(request))
    }

}
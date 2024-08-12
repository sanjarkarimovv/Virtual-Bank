package uz.androbeck.virtualbank.data.source.remote.home

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRemoteDataSource {

    override fun getFullInfo() = flow {
        emit(homeService.getFullInfo())
    }

    override fun getBasicInfo() = flow {
        emit(homeService.getBasicInfo())
    }

    override fun putUpdateInfo(request: UpdateInfoReqDto) = flow {
        emit(homeService.putUpdateInfo(request))
    }
    override fun getLastTransfers()= flow {
        emit(homeService.getLastTransfers())
    }

}
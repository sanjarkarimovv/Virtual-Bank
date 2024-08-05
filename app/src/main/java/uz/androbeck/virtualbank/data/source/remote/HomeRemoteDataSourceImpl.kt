package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.dto.common.response.home_response.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
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

}
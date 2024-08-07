package uz.androbeck.virtualbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.UpdateInfoReqDto
import uz.androbeck.virtualbank.data.source.remote.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override fun getFullInfo() = homeRemoteDataSource.getFullInfo()

    override fun getBasicInfo() = homeRemoteDataSource.getBasicInfo()

    override fun putUpdateInfo(request: UpdateInfoReqDto) =
            homeRemoteDataSource.putUpdateInfo(request)

}
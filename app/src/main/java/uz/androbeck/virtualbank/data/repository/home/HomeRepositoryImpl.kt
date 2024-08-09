package uz.androbeck.virtualbank.data.repository.home

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

    override fun getTotalBalance()= homeRemoteDataSource.getTotalBalance()
}
package uz.androbeck.virtualbank.data.repository.home

import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
) : HomeRepository {

    override fun getFullInfo() = homeRemoteDataSource.getFullInfo()

    override fun getBasicInfo() = homeRemoteDataSource.getBasicInfo()

    override fun putUpdateInfo(request: UpdateInfoReqDto) =
            homeRemoteDataSource.putUpdateInfo(request)

}
package uz.androbeck.virtualbank.data.repository.home

import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.data.source.local.home.HomeLocalDatasource
import uz.androbeck.virtualbank.data.dto.response.home.TotalBalanceResDto
import uz.androbeck.virtualbank.data.source.remote.home.HomeRemoteDataSource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val homeLocalDatasource: HomeLocalDatasource
) : HomeRepository {

    override fun getFullInfo() = homeRemoteDataSource.getFullInfo()

    override fun getBasicInfo() = homeRemoteDataSource.getBasicInfo()

    override fun putUpdateInfo(request: UpdateInfoReqDto) =
        homeRemoteDataSource.putUpdateInfo(request)

    override fun getTotalBalance() = homeRemoteDataSource.getTotalBalance()

    override fun getAllHomeComponents() =
        homeLocalDatasource.getHomeComponents()

    override suspend fun putComponents(homeEntity: HomeEntity) {
        homeLocalDatasource.putHomeComponent(homeEntity)
    }

    override suspend fun updateComponents(id: Int, isVisibility: Boolean) {
        homeLocalDatasource.updateItem(id, isVisibility)
    }


}
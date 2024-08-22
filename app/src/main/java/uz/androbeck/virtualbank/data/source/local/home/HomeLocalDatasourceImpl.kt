package uz.androbeck.virtualbank.data.source.local.home

import uz.androbeck.virtualbank.data.db.dao.HomeDao
import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import javax.inject.Inject

class HomeLocalDatasourceImpl @Inject constructor(
    private val homeDao: HomeDao
) : HomeLocalDatasource {
    override fun getHomeComponents() = homeDao.getAllComponents()

    override suspend fun putHomeComponent(homeEntity: HomeEntity) {
        homeDao.addComponent(homeEntity)
    }

    override suspend fun updateItem(id: Int, isVisibility: Boolean) {
        homeDao.updateComponent(id,isVisibility)
    }


}
package uz.androbeck.virtualbank.data.source.local.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.db.entity.HomeEntity


interface HomeLocalDatasource {
    fun getHomeComponents(): Flow<List<HomeEntity>>
    suspend fun putHomeComponent(homeEntity: HomeEntity)
    suspend fun updateItem(id:Int,isVisibility: Boolean)
}
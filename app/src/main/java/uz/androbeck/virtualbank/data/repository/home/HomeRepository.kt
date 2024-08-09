package uz.androbeck.virtualbank.data.repository.home

import androidx.transition.Visibility
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.db.entity.HomeEntity
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.data.dto.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.response.home.FullInfoResDto

interface HomeRepository {
    fun getFullInfo(): Flow<FullInfoResDto>
    fun getBasicInfo(): Flow<BasicInfoResDto>
    fun putUpdateInfo(request: UpdateInfoReqDto): Flow<MessageResDto>
    fun getAllHomeComponents(): Flow<List<HomeEntity>>
    suspend fun putComponents(homeEntity: HomeEntity)
    suspend fun updateComponents(id: Int, isVisibility: Boolean)
}
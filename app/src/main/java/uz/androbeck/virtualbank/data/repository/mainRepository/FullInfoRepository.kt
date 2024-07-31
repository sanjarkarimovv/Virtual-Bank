package uz.androbeck.virtualbank.data.repository.mainRepository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface FullInfoRepository {
    fun getFullInfo(token:String?):Flow<FullInfoDto>
}
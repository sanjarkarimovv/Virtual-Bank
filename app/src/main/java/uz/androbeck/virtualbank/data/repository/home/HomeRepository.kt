package uz.androbeck.virtualbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface HomeRepository {
    fun getFullInfo(token:String?):Flow<FullInfoDto>
}
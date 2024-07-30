package uz.androbeck.virtualbank.data.repository.mainRepository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto

interface MainRepository {
    fun getFullInfo():Flow<MainFullInfoDto>
}
package uz.androbeck.virtualbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.TotalBalanceDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface HomeRepository {
    fun getFullInfo():Flow<FullInfoDto>
    fun getTotalBalance(): Flow<TotalBalanceDto>
}
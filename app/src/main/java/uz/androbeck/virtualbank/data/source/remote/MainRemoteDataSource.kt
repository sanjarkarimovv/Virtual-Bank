package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto

interface MainRemoteDataSource {
    fun getFullInfo():Flow<MainFullInfoDto>
}
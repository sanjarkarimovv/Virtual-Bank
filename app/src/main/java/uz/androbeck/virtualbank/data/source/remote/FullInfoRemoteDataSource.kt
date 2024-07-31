package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface FullInfoRemoteDataSource {
    fun getFullInfo(token:String?):Flow<FullInfoDto>
}
package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface HomeRemoteDataSource {
    fun getFullInfo():Flow<FullInfoDto>
}
package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.home_response.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto

interface HomeRemoteDataSource {
    fun getFullInfo(): Flow<FullInfoDto>
    fun getBasicInfo(): Flow<BasicInfoResDto>
}
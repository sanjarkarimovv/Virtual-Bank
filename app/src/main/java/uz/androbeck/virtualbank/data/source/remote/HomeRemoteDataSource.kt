package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.response.home.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto

interface HomeRemoteDataSource {
    fun getFullInfo(): Flow<FullInfoDto>
    fun getBasicInfo(): Flow<BasicInfoResDto>
    fun putUpdateInfo(request: UpdateInfoReqDto):Flow<MessageResDto>

}
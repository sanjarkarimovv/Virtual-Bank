package uz.androbeck.virtualbank.data.source.remote.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.home.TotalBalanceResDto
import uz.androbeck.virtualbank.data.dto.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.response.home.FullInfoResDto
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto

interface HomeRemoteDataSource {
    fun getFullInfo(): Flow<FullInfoResDto>
    fun getBasicInfo(): Flow<BasicInfoResDto>
    fun getTotalBalance():Flow<TotalBalanceResDto>
    fun putUpdateInfo(request: UpdateInfoReqDto):Flow<MessageResDto>

}
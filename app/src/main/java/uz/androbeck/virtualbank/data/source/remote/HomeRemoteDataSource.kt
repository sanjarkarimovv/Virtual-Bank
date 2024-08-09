package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.common.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.common.response.TotalBalanceResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.UpdateInfoReqDto

interface HomeRemoteDataSource {
    fun getFullInfo(): Flow<FullInfoDto>
    fun getBasicInfo(): Flow<BasicInfoResDto>
    fun putUpdateInfo(request: UpdateInfoReqDto):Flow<MessageResDto>
    fun getTotalBalance():Flow<TotalBalanceResDto>


}
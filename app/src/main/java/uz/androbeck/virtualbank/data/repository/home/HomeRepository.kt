package uz.androbeck.virtualbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.UpdateInfoReqDto

interface HomeRepository {

    fun getFullInfo():Flow<FullInfoDto>

    fun putUpdateInfo(request: UpdateInfoReqDto):Flow<MessageResDto>

}
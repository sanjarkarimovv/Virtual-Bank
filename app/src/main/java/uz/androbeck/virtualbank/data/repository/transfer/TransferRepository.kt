package uz.androbeck.virtualbank.data.repository.transfer

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TrasnferRequestDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto

interface TransferRepository {
    fun getFee(request: GetFeeReqDto): Flow<GetFeeResDto>
    fun transfer(request: TrasnferRequestDto): Flow<TokenResDto>
}
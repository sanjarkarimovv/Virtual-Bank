package uz.androbeck.virtualbank.data.source.remote.transfer

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TrasnferRequestDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto

interface TransferRemoteDataSource  {
    fun getFee(request: GetFeeReqDto): Flow<GetFeeResDto>
    fun moneyTransfer(transferReqDto: TrasnferRequestDto): Flow<TokenResDto>

}
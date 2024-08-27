package uz.androbeck.virtualbank.data.repository.transfer

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetCardOwnerByPanReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TransferRequestDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetCardOwnerByPanResDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto

interface TransferRepository {
    fun getFee(request: GetFeeReqDto): Flow<GetFeeResDto>
    fun transfer(request: TransferRequestDto): Flow<TokenResDto>
    fun getCardOwner(request: GetCardOwnerByPanReqDto): Flow<GetCardOwnerByPanResDto>

    fun transferVerify(request: CodeVerifyReqDto): Flow<MessageResDto>
}
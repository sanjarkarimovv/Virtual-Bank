package uz.androbeck.virtualbank.data.repository.transfer

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TransferRequestDto
import uz.androbeck.virtualbank.data.source.remote.transfer.TransferRemoteDataSource
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferRemoteDataSource: TransferRemoteDataSource,
) : TransferRepository {
    override fun getFee(request: GetFeeReqDto) = transferRemoteDataSource.getFee(request)
    override fun transfer(request: TransferRequestDto) = transferRemoteDataSource.transfer(request)
    override fun transferVerify(request: CodeVerifyReqDto) = transferRemoteDataSource.transferVerify(request)
}
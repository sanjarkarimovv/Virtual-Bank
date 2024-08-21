package uz.androbeck.virtualbank.data.repository.transfer

import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TrasnferRequestDto
import uz.androbeck.virtualbank.data.source.remote.transfer.TransferRemoteDataSource
import javax.inject.Inject

class TransferRepositoryImpl @Inject constructor(
    private val transferRemoteDataSource: TransferRemoteDataSource,

    ) : TransferRepository {
    override fun getFee(request: GetFeeReqDto) = transferRemoteDataSource.getFee(request)
    override fun moneyTransfer(request: TrasnferRequestDto) = transferRemoteDataSource.moneyTransfer(request)
}
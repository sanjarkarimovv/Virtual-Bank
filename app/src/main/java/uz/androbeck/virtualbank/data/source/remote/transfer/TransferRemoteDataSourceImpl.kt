package uz.androbeck.virtualbank.data.source.remote.transfer

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.TransferService
import uz.androbeck.virtualbank.data.dto.request.transfer.GetCardOwnerByPanReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.request.transfer.TransferRequestDto
import javax.inject.Inject

class TransferRemoteDataSourceImpl @Inject constructor(
    private val transferService: TransferService,
) : TransferRemoteDataSource {

    override fun getFee(request: GetFeeReqDto) = flow {
        emit(transferService.getFee(request))
    }
    override fun transfer(transferReqDto: TransferRequestDto) = flow {
        emit(transferService.transfer(transferReqDto))
    }

    override fun getCardOwner(getCardOwnerByPanReqDto: GetCardOwnerByPanReqDto) = flow {
        emit(transferService.getCardOwner(getCardOwnerByPanReqDto))
    }
}
package uz.androbeck.virtualbank.data.source.remote.transfer

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.TransferService
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import javax.inject.Inject

class TransferRemoteDataSourceImpl @Inject constructor(
    private val transferService: TransferService,
) : TransferRemoteDataSource {

    override fun getFee(request: GetFeeReqDto) = flow {
        emit(transferService.getFee(request))
    }
}
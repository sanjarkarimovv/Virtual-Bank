package uz.androbeck.virtualbank.data.source.remote.transfer

import dagger.Provides
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.api.TransferService
import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto
import javax.inject.Inject

interface TransferRemoteDataSource  {
    fun getFee(request: GetFeeReqDto): Flow<GetFeeResDto>
}
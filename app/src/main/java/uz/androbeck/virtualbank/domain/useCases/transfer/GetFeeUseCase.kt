package uz.androbeck.virtualbank.domain.useCases.transfer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.domain.mapper.transfer.GetFeeReqMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.GetFeeResMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetFeeReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetFeeResUIModel
import javax.inject.Inject


class GetFeeUseCase @Inject constructor(
    private val transferRepository: TransferRepository,
    private val getReqMapper: GetFeeReqMapper,
    private val getFeeResMapper: GetFeeResMapper,
){
    operator fun invoke(uiReqModel: GetFeeReqUIModel): Flow<GetFeeResUIModel>{
        val request = getReqMapper.toDTO(uiReqModel)
        return transferRepository.getFee(request).map { getFeeResMapper.toUIModel(it) }
    }

}
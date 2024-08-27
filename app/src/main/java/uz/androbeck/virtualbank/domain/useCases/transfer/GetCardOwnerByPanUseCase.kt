package uz.androbeck.virtualbank.domain.useCases.transfer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.domain.mapper.transfer.GetCardOwnerByPanReqMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.GetCardOwnerByPanResMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanResUIModel
import javax.inject.Inject

class GetCardOwnerByPanUseCase @Inject constructor(
    private val transferRepository: TransferRepository,
    private val getReqMapper: GetCardOwnerByPanReqMapper,
    private val getResMapper: GetCardOwnerByPanResMapper
){
    operator fun invoke(uiReqModel: GetCardOwnerByPanReqUIModel): Flow<GetCardOwnerByPanResUIModel>{
        val request = getReqMapper.toDTO(uiReqModel)
        return transferRepository.getCardOwner(request).map { getResMapper.toUIModel(it) }
    }
}
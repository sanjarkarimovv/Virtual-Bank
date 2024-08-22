package uz.androbeck.virtualbank.domain.useCases.transfer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.GetFeeResMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.TransferMapper
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import javax.inject.Inject

class TransferUseCase@Inject constructor(
    private val transferRepository: TransferRepository,
    private val transferMapper: TransferMapper,
    private val tokenMapper: TokenMapper,
) {
    operator fun invoke(uiReqModel: TransferUIModel): Flow<TokenUIModel> {
        val request = transferMapper.toDTO(uiReqModel)
        return transferRepository.transfer(request).map { tokenMapper.toUIModel(it) }
    }
}

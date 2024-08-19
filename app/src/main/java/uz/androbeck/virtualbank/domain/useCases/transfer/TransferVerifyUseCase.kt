package uz.androbeck.virtualbank.domain.useCases.transfer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.TransferVerifyMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject

class TransferVerifyUseCase @Inject constructor(
    private val repository: TransferRepository,
    private val mapper: TransferVerifyMapper,
    private val messageMapper: MessageMapper
) {
    operator fun invoke(dto: CodeVerifyReqDto) =
        repository.transferVerify(dto).map { messageMapper.toUIModel(it) }

}
package uz.androbeck.virtualbank.domain.useCases.transfer

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.repository.transfer.TransferRepository
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.mapper.transfer.TransferVerifyMapper

class TransferVerifyUseCase(
    private val repository: TransferRepository,
    private val mapper: TransferVerifyMapper,
    private val messageMapper: MessageMapper
) {
    operator fun invoke(request: CodeVerifyReqDto) =
        repository.transferVerify(request).map {
            messageMapper.toUIModel(it)
        }
}
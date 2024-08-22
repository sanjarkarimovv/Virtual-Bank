package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.request.transfer.TransferRequestDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import javax.inject.Inject

class TransferMapper @Inject constructor(
) : BaseMapper<TransferRequestDto, TransferUIModel> {
    override fun toUIModel(dto: TransferRequestDto) = dto.run {
        TransferUIModel(
            type = type,
            sender_id = sender_id,
            receiver_id = receiver_pan,
            amount = amount
        )
    }

    override fun toDTO(uiModel: TransferUIModel) = uiModel.run {
        TransferRequestDto(
            type = type,
            sender_id = sender_id,
            receiver_pan = receiver_id,
            amount = amount
        )
    }
}
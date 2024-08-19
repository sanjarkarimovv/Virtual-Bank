package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.request.transfer.TrasnferRequestDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.TransferUIModel
import javax.inject.Inject

class TransferMapper @Inject constructor(
) :BaseMapper<TrasnferRequestDto,TransferUIModel>{
    override fun toUIModel(dto: TrasnferRequestDto) = dto.run {
        TransferUIModel(
            type = type,
            sender_id =sender_id,
            receiver_id=receiver_id,
            amount = amount
        )
    }

    override fun toDTO(uiModel: TransferUIModel)= uiModel.run {
        TrasnferRequestDto(
            type = type,
            sender_id =sender_id,
            receiver_id=receiver_id,
            amount = amount
        )
    }
}
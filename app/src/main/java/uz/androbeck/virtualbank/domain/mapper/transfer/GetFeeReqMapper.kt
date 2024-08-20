package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.request.transfer.GetFeeReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetFeeReqUIModel
import javax.inject.Inject

class GetFeeReqMapper @Inject constructor():BaseMapper<GetFeeReqDto, GetFeeReqUIModel> {


    override fun toDTO(uiModel: GetFeeReqUIModel)=uiModel.run {
        GetFeeReqDto(
            sender_id = senderId,
            receiver = receiver,
            amount = amount
        )
    }

    override fun toUIModel(dto: GetFeeReqDto)=dto.run {
        GetFeeReqUIModel(
            senderId = sender_id,
            receiver = receiver,
            amount = amount
        )
    }
}
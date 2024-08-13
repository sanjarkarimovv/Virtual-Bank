package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject

class DeleteCardMapper @Inject constructor():BaseMapper<MessageResDto,MessageUIModel> {
    override fun toUIModel(dto: MessageResDto)=dto.run {
        MessageUIModel(
            message=message
        )
    }

    override fun toDTO(uiModel: MessageUIModel)=uiModel.run {
        MessageResDto(
            message=message
        )
    }
}
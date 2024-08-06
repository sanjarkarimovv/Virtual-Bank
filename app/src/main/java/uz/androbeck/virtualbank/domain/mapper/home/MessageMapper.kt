package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageMapper @Inject constructor() : BaseMapper<MessageResDto, MessageUIModel> {
    override fun toUIModel(dto: MessageResDto): MessageUIModel = dto.run {
        MessageUIModel(
            message = message
        )
    }
    override fun toDTO(uiModel: MessageUIModel): MessageResDto = uiModel.run {
        MessageResDto(
            message = message
        )
    }
}
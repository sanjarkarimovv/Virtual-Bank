package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.card.UpdateCardUIModel

class UpdateCardMapper : BaseMapper<UpdateCardReqDto, UpdateCardUIModel> {
    override fun toUIModel(dto: UpdateCardReqDto) = dto.run {
        UpdateCardUIModel(
            id = id,
            name = name,
            themeType = theme_type,
            isVisible = is_visible
        )
    }

    override fun toDTO(uiModel: UpdateCardUIModel) = uiModel.run {
        UpdateCardReqDto(
            id = id,
            name = name,
            theme_type = themeType,
            is_visible = isVisible
        )
    }
}
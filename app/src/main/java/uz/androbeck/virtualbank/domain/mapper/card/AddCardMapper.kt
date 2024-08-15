package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.card.AddCardReqUIModel
import javax.inject.Inject

class AddCardMapper @Inject constructor() : BaseMapper<AddCardReqDto, AddCardReqUIModel> {
    override fun toUIModel(dto: AddCardReqDto) = dto.run {
        AddCardReqUIModel(
            pan = pan,
            expiredYear = expired_year,
            expiredMonth = expired_month,
            name = name
        )
    }

    override fun toDTO(uiModel: AddCardReqUIModel) = uiModel.run {
        AddCardReqDto(
            pan = pan,
            expired_year = expiredYear,
            expired_month = expiredMonth,
            name = name
        )
    }
}
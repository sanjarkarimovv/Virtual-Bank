package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCardsMapper @Inject constructor() :
    BaseMapper<CardResDto, CardUIModel> {
    override fun toUIModel(dto: CardResDto) = dto.run {
        CardUIModel(
            id = id,
            name = name,
            owner = owner,
            amount = amount,
            expiredYear = expiredYear,
            expiredMonth = expiredMonth,
            pan = pan,
            themeType = themeType,
            isVisible = isVisible
        )
    }

    override fun toDTO(uiModel: CardUIModel) = uiModel.run {
        CardResDto(
            id = id,
            name = name,
            amount = amount,
            owner = owner,
            pan = pan,
            expiredYear = expiredYear,
            expiredMonth = expiredMonth,
            themeType = themeType,
            isVisible = isVisible
        )
    }
}
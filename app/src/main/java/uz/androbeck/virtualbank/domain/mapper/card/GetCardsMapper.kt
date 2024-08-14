package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.domain.ui_models.cards.GetCardsUIModel
import javax.inject.Inject

class GetCardsMapper @Inject constructor(): BaseMapper<CardResDto, GetCardsUIModel> {
    override fun toUIModel(dto: CardResDto) = dto.run {
        GetCardsUIModel(
            getCardsUIModel = car.map {
                CardUIModel(
                    id = dto.id,
                    name = it.name,
                    amount = it.amount,
                    owner = it.owner,
                    pan = it.pan,
                    expiredYear = it.expiredYear,
                    expiredMonth = it.expiredMonth,
                    themeType = it.themeType,
                    isVisible = it.isVisible
                )
            }
        )
    }

    override fun toDTO(uiModel: GetCardsUIModel): CardResDto {
        TODO("Not yet implemented")
    }
}
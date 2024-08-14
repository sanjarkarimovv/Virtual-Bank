package uz.androbeck.virtualbank.domain.mapper.cards

import uz.androbeck.virtualbank.data.dto.response.cards.CardsResDto
import uz.androbeck.virtualbank.data.dto.response.cards.InComeCardResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.cards.CardInfoUIModel
import uz.androbeck.virtualbank.domain.ui_models.cards.GetCardsUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCardsMapper @Inject constructor() :
    BaseMapper<CardsResDto, GetCardsUIModel> {
    override fun toUIModel(dto: CardsResDto) = dto.run {
        GetCardsUIModel(
            cardUIModel = cardsDto?.map {
                CardInfoUIModel(
                    id = it.id,
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

    override fun toDTO(uiModel: GetCardsUIModel) = uiModel.run {
        CardsResDto(
            cardsDto = cardUIModel?.map {
                InComeCardResDto(
                    id = it.id,
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


}
package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.response.card.CardResDto
import uz.androbeck.virtualbank.data.dto.response.card.GetCardsResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.cards.CardInfoUIModel
import uz.androbeck.virtualbank.domain.ui_models.cards.GetCardsUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCardsMapper @Inject constructor() :
    BaseMapper<GetCardsResDto, GetCardsUIModel> {
    override fun toUIModel(dto: GetCardsResDto) = dto.run {
        GetCardsUIModel(
            cardUIModel = cardResDto?.map {
                CardInfoUIModel(
                    id = it.id,
                    name = it.name,
                    owner = it.owner,
                    amount = it.amount,
                    expiredYear = it.expiredYear,
                    expiredMonth = it.expiredMonth,
                )
            }
        )
    }

    override fun toDTO(uiModel: GetCardsUIModel) = uiModel.run {
        GetCardsResDto(
            cardResDto = cardUIModel?.map {
                CardResDto(
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
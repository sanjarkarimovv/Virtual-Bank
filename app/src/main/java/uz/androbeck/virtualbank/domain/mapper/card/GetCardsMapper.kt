package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.common.response.GetCardsResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.domain.ui_models.cards.GetCardsUIModel
import javax.inject.Inject

class GetCardsMapper @Inject constructor(): BaseMapper<GetCardsResDto, GetCardsUIModel> {
    override fun toUIModel(dto: GetCardsResDto) = dto.run {
        GetCardsUIModel(
            getCardsUIModel = dto.map {
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

    override fun toDTO(uiModel: GetCardsUIModel): GetCardsResDto {
        TODO("Not yet implemented")
    }
}
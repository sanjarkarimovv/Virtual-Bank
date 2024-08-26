package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import javax.inject.Inject

class GetHistoryMapper @Inject constructor() :
    BaseMapper<InComeAndOutComeResDto, InComeAndOutComeUIModel> {
    override fun toUIModel(dto: InComeAndOutComeResDto) = dto.run {
        InComeAndOutComeUIModel(
            type = type,
            from = from,
            to = to,
            amount = amount,
            time = time,
        )
    }

    override fun toDTO(uiModel: InComeAndOutComeUIModel) = uiModel.run {

        InComeAndOutComeResDto(
            type = type,
            from = from,
            to = to,
            amount = amount,
            time = time
        )
    }
}
package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransfersMapper @Inject constructor() :
    BaseMapper<InComeAndOutComeResDto, InComeAndOutComeUIModel> {
    override fun toUIModel(dto: InComeAndOutComeResDto): InComeAndOutComeUIModel {
        return InComeAndOutComeUIModel(
            type = dto.type,
            from = dto.from,
            to = dto.to,
            amount = dto.amount,
            time = dto.time
        )
    }

    override fun toDTO(uiModel: InComeAndOutComeUIModel): InComeAndOutComeResDto {
        return InComeAndOutComeResDto(
            type = uiModel.type,
            from = uiModel.from,
            to = uiModel.to,
            amount = uiModel.amount,
            time = uiModel.time
        )
    }

}

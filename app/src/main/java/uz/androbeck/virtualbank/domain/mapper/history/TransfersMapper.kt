package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.TransfersUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransfersMapper @Inject constructor() :
    BaseMapper<LastTransfersResDto, TransfersUIModel> {

    override fun toUIModel(dto: LastTransfersResDto) = dto.run {
        TransfersUIModel(
            transferUIModel = transferResDto?.map {
                InComeAndOutComeUIModel(
                    type = it.type,
                    from = it.from,
                    to = it.to,
                    amount = it.amount,
                    time = it.time,
                )
            }
        )
    }
    override fun toDTO(uiModel: TransfersUIModel) = uiModel.run {
        LastTransfersResDto(
            transferResDto = transferUIModel?.map {
                InComeAndOutComeResDto(
                    type = it.type,
                    from = it.from,
                    to = it.to,
                    amount = it.amount,
                    time = it.time,
                )
            }
        )
    }
}

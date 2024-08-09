package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto
import uz.androbeck.virtualbank.data.dto.response.history.InComeAndOutComeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.LastTransferUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastTransfersMapper @Inject constructor() :
    BaseMapper<LastTransfersResDto, LastTransferUIModel> {

    override fun toUIModel(dto: LastTransfersResDto) = dto.run {
        LastTransferUIModel(
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
    override fun toDTO(uiModel: LastTransferUIModel) = uiModel.run {
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

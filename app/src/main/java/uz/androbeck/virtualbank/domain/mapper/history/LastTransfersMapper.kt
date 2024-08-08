package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.response.history.LastTransfersResDto
import uz.androbeck.virtualbank.data.dto.response.history.InComeAndOutComeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.LastTransferUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.TransferUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LastTransfersMapper @Inject constructor() :
    BaseMapper<LastTransfersResDto, LastTransferUIModel> {

    override fun toUIModel(dto: LastTransfersResDto) = dto.run {
        LastTransferUIModel(
            transferUIModel = transferResDto?.map {
                TransferUIModel(
                    type = it.type,
                    from = it.from,
                    to = it.to,
                    amount = it.amount
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
                )
            }
        )
    }
}

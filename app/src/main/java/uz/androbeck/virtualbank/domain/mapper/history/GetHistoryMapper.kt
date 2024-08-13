package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.history.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.TransfersUIModel
import javax.inject.Inject

class GetHistoryMapper @Inject constructor():BaseMapper<GetHistoryResDto, TransfersUIModel> {
    override fun toUIModel(dto: GetHistoryResDto)=dto.run {
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

    override fun toDTO(uiModel: TransfersUIModel)=uiModel.run {
        GetHistoryResDto(
          transferResDto = transferUIModel?.map {
              InComeAndOutComeResDto(
                  type = it.type,
                  from = it.from,
                  to = it.to,
                  amount = it.amount,)
          }
        )
    }
}
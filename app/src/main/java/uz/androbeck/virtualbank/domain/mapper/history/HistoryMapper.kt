package uz.androbeck.virtualbank.domain.mapper.history

import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.InComeAndOutComeUIModel
import uz.androbeck.virtualbank.domain.ui_models.history.GetHistoryUIModel

class HistoryMapper:BaseMapper<GetHistoryResDto, GetHistoryUIModel> {
    override fun toUIModel(dto: GetHistoryResDto)=dto.run {
        GetHistoryUIModel(
            total_elements = total_elements,
            total_pages = total_pages,
            current_page = current_page,
            transfers = transfers?.map {
                InComeAndOutComeUIModel(
                    type = it.type,
                    from = it.from,
                    to = it.to,
                    amount = it.amount,
                    time = it.time,)
            }
        )
    }

    override fun toDTO(uiModel: GetHistoryUIModel)=uiModel.run {
        GetHistoryResDto(
            total_elements = total_elements,
            total_pages = total_pages,
            current_page = current_page,
            transfers = transfers?.map {
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

package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.response.transfer.GetFeeResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetFeeResUIModel
import javax.inject.Inject

class GetFeeResMapper @Inject constructor(): BaseMapper<GetFeeResDto, GetFeeResUIModel> {
    override fun toUIModel(dto: GetFeeResDto)=dto.run {
        GetFeeResUIModel(
            fee = fee,
            amount = amount
        )

    }

    override fun toDTO(uiModel: GetFeeResUIModel)=uiModel.run {
        GetFeeResDto(
            fee = fee,
            amount = amount
        )

    }


}
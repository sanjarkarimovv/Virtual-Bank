package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.response.transfer.GetCardOwnerByPanResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanResUIModel
import javax.inject.Inject

class GetCardOwnerByPanResMapper @Inject constructor():
    BaseMapper<GetCardOwnerByPanResDto, GetCardOwnerByPanResUIModel> {
        override fun toDTO(uiModel: GetCardOwnerByPanResUIModel)=uiModel.run {
        GetCardOwnerByPanResDto(
            pan = pan
        )
    }
    override fun toUIModel(dto: GetCardOwnerByPanResDto)=dto.run {
        GetCardOwnerByPanResUIModel(
            pan = pan
        )
    }
}
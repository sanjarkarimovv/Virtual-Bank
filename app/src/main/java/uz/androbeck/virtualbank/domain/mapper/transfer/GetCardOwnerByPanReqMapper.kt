package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.request.transfer.GetCardOwnerByPanReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetCardOwnerByPanReqUIModel
import javax.inject.Inject

class GetCardOwnerByPanReqMapper @Inject constructor() :
    BaseMapper<GetCardOwnerByPanReqDto, GetCardOwnerByPanReqUIModel> {
    override fun toDTO(uiModel: GetCardOwnerByPanReqUIModel) = uiModel.run {
        GetCardOwnerByPanReqDto(
            pan = pan
        )
    }

    override fun toUIModel(dto: GetCardOwnerByPanReqDto) = dto.run {
        GetCardOwnerByPanReqUIModel(
            pan = pan
        )

    }
}
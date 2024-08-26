package uz.androbeck.virtualbank.domain.mapper.card

import uz.androbeck.virtualbank.data.dto.request.card.DeleteCardReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.card.DeleteCardReqUIModel
import javax.inject.Inject

class DeleteCardMapper @Inject constructor():BaseMapper<DeleteCardReqDto,DeleteCardReqUIModel> {

    override fun toUIModel(dto: DeleteCardReqDto)=dto.run {
        DeleteCardReqUIModel(
           id=id
        )
    }

    override fun toDTO(uiModel: DeleteCardReqUIModel)=uiModel.run {
        DeleteCardReqDto(
          id=id
        )
    }
}
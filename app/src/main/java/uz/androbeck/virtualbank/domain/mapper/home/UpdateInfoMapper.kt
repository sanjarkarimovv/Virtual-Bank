package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.home.UpdateInfoUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateInfoMapper @Inject constructor() : BaseMapper<UpdateInfoReqDto, UpdateInfoUIModel> {
    override fun toUIModel(dto: UpdateInfoReqDto): UpdateInfoUIModel =dto.run {
        UpdateInfoUIModel(
            firstName = first_name,
            lastName = last_name,
            bornDate = born_date,
            gender = gender_type
        )
    }

    override fun toDTO(uiModel: UpdateInfoUIModel): UpdateInfoReqDto =uiModel.run {
        UpdateInfoReqDto(
            first_name =firstName,
            last_name = lastName,
            born_date = bornDate,
            gender_type = gender
        )
    }


}
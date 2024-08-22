package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateInfoMapper @Inject constructor() : BaseMapper<UpdateInfoReqDto, UpdateInfoReqUIModel> {
    override fun toUIModel(dto: UpdateInfoReqDto): UpdateInfoReqUIModel =dto.run {
        UpdateInfoReqUIModel(
            firstName = first_name,
            lastName = last_name,
            bornDate = born_date,
            gender = gender_type
        )
    }

    override fun toDTO(uiModel: UpdateInfoReqUIModel): UpdateInfoReqDto =uiModel.run {
        UpdateInfoReqDto(
            first_name =firstName,
            last_name = lastName,
            born_date = bornDate,
            gender_type = gender
        )
    }


}
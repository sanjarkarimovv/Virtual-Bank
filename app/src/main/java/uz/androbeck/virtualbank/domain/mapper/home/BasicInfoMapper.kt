package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.home.BasicInfoUiModel
import javax.inject.Inject

class BasicInfoMapper @Inject constructor() : BaseMapper<BasicInfoResDto, BasicInfoUiModel> {
    override fun toUIModel(dto: BasicInfoResDto) = dto.run {
        BasicInfoUiModel(
            firstname = first_name,
            genderType = gender_type,
            age = age
        )
    }

    override fun toDTO(uiModel: BasicInfoUiModel) = uiModel.run {
        BasicInfoResDto(
            first_name = firstname,
            gender_type = genderType,
            age = age
        )
    }
}
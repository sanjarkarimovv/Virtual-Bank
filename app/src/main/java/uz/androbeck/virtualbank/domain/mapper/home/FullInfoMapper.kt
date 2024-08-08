package uz.androbeck.virtualbank.domain.mapper.home

import uz.androbeck.virtualbank.data.dto.response.home.FullInfoDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FullInfoMapper @Inject constructor() : BaseMapper<FullInfoDto, FullInfoUIModel> {
    override fun toUIModel(dto: FullInfoDto) = dto.run {
        FullInfoUIModel(
            firstName = first_name,
            lastName = last_name,
            phone = phone,
            bornDate = born_date,
            gender = gender
        )
    }
    override fun toDTO(uiModel: FullInfoUIModel) = uiModel.run {
        FullInfoDto(
            first_name = firstName,
            last_name = lastName,
            phone = phone,
            born_date = bornDate,
            gender = gender
        )
    }

}
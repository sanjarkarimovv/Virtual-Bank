package uz.androbeck.virtualbank.domain.mapper.main

import dagger.Provides
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.main.FullInfoUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFullInfoMapper @Inject constructor() : BaseMapper<MainFullInfoDto, FullInfoUIModel> {
    override fun toUIModel(dto: MainFullInfoDto): FullInfoUIModel = dto.run {
        FullInfoUIModel(
            firstName = first_name,
            lastName = last_name,
            password = password,
            phone = phone,
            bornDate = born_date,
            gender = gender
        )
    }
    override fun toDTO(uiModel: FullInfoUIModel): MainFullInfoDto = uiModel.run {
        MainFullInfoDto(
            first_name = firstName,
            last_name = lastName,
            password = password,
            phone = phone,
            born_date = bornDate,
            gender = gender
        )
    }

}
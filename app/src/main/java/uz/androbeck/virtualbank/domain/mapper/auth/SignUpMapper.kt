package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpMapper @Inject constructor() : BaseMapper<SignUpReqDto, SignUpReqUIModel> {
    override fun toUIModel(dto: SignUpReqDto) = dto.run {
        SignUpReqUIModel(
            firstName = first_name,
            lastName = last_name,
            password = password,
            phone = phone,
            bornDate = born_date,
            gender = gender?.toInt()
        )
    }

    override fun toDTO(uiModel: SignUpReqUIModel) = uiModel.run {
        SignUpReqDto(
            first_name = firstName,
            last_name = lastName,
            password = password,
            phone = phone,
            born_date = bornDate,
            gender = gender.toString()
        )
    }
}
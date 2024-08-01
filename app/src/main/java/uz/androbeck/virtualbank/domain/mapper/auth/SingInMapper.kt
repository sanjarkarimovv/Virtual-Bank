package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.SignInReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SingInMapper @Inject constructor() : BaseMapper<SignInReqDto, SignInReqUIModel> {
    override fun toUIModel(dto: SignInReqDto) = dto.run {
        SignInReqUIModel(
            password = password, phone = phone
        )
    }

    override fun toDTO(uiModel: SignInReqUIModel) = uiModel.run {
        SignInReqDto(
            password = password, phone = phone
        )
    }
}
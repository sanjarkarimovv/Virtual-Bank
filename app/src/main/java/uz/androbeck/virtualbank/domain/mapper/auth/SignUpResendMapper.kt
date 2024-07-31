package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.SignUpResendReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpResendReqUIModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignUpResendMapper @Inject constructor() :
    BaseMapper<SignUpResendReqDto, SignUpResendReqUIModel> {
    override fun toUIModel(dto: SignUpResendReqDto) = dto.run {
        SignUpResendReqUIModel(
            token = token
        )
    }

    override fun toDTO(uiModel: SignUpResendReqUIModel) = uiModel.run {
        SignUpResendReqDto(
            token = token
        )
    }
}
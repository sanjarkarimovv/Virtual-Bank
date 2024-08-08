package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInVerifyReqUIModel
import javax.inject.Inject

class SignInVerifyMapper @Inject constructor() :
    BaseMapper<SignInVerifyReqDto, SignInVerifyReqUIModel> {
    override fun toUIModel(dto: SignInVerifyReqDto) = dto.run {
        SignInVerifyReqUIModel(
            accessToken = dto.accessToken,
            refreshToken = dto.refreshToken
        )
    }

    override fun toDTO(uiModel: SignInVerifyReqUIModel) = uiModel.run {
        SignInVerifyReqDto(
            accessToken = uiModel.accessToken,
            refreshToken = uiModel.refreshToken
        )
    }
}
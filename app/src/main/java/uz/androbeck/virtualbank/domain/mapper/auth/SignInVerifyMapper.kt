package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.SignInVerifyReDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInVerifyReqUIModel
import javax.inject.Inject

class SignInVerifyMapper @Inject constructor() :
    BaseMapper<SignInVerifyReDto, SignInVerifyReqUIModel> {
    override fun toUIModel(dto: SignInVerifyReDto) = dto.run {
        SignInVerifyReqUIModel(
            token = dto.token,
            code = dto.code
        )
    }

    override fun toDTO(uiModel: SignInVerifyReqUIModel) = uiModel.run {
        SignInVerifyReDto(
            token = uiModel.token,
            code = uiModel.code
        )
    }
}
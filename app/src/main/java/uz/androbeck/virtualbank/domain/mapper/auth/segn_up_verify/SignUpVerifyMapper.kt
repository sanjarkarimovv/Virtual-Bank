package uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify

import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpVerifyMapper @Inject constructor() :
    BaseMapper<SignUpVerifyReqDto, SignUpVerifyReqUIModel> {
    override fun toUIModel(dto: SignUpVerifyReqDto)= dto.run {
        SignUpVerifyReqUIModel(
            token = token,
            code = code
        )
    }

    override fun toDTO(uiModel: SignUpVerifyReqUIModel)=uiModel.run {
        SignUpVerifyReqDto(
            token = token,
            code = code
        )
    }
}
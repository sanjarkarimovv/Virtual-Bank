package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SingInResendReqUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SingInResendMapper @Inject constructor() :
    BaseMapper<TokenReqDto, SingInResendReqUiModel> {
    override fun toUIModel(dto: TokenReqDto) = dto.run {
        SingInResendReqUiModel(
            token = token
        )
    }

    override fun toDTO(uiModel: SingInResendReqUiModel) = uiModel.run {
        TokenReqDto(
            token = token
        )
    }
}
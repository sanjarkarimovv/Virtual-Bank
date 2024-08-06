package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.TokenReqUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpResendMapper @Inject constructor() :
    BaseMapper<TokenReqDto, TokenReqUiModel> {
    override fun toUIModel(dto: TokenReqDto) = dto.run {
        TokenReqUiModel(
            token = token
        )
    }

    override fun toDTO(uiModel: TokenReqUiModel) = uiModel.run {
        TokenReqDto(
            token = token
        )
    }
}
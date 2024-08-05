package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.TokenReqUIModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignUpResendMapper @Inject constructor() :
    BaseMapper<TokenReqDto, TokenReqUIModel> {
    override fun toUIModel(dto: TokenReqDto) = dto.run {
        TokenReqUIModel(
            token = token
        )
    }

    override fun toDTO(uiModel: TokenReqUIModel) = uiModel.run {
        TokenReqDto(
            token = token
        )
    }
}
package uz.androbeck.virtualbank.domain.mapper.auth.common

import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.TokensUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokensMapper @Inject constructor() :
    BaseMapper<TokensResDto, TokensUIModel> {
    override fun toUIModel(dto: TokensResDto) = dto.run {
        TokensUIModel(
            refreshToken = refresh_token,
            accessToken = access_token
        )
    }

    override fun toDTO(uiModel: TokensUIModel) = uiModel.run {
        TokensResDto(
            refresh_token = refreshToken,
            access_token = accessToken
        )
    }
}
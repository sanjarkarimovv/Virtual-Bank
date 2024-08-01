package uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify

import uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response.TokensResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyTokenUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpVerifyTokenMapper @Inject constructor() :
    BaseMapper<TokensResDto, SignUpVerifyTokenUIModel> {
    override fun toUIModel(dto: TokensResDto) = dto.run {
        SignUpVerifyTokenUIModel(
            refreshToken = refresh_token,
            accessToken = access_token
        )
    }

    override fun toDTO(uiModel: SignUpVerifyTokenUIModel) = uiModel.run {
        TokensResDto(
            refresh_token = refreshToken,
            access_token = accessToken
        )
    }
}
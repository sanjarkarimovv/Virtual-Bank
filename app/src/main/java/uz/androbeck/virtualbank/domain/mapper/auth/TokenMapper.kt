package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenMapper @Inject constructor() : BaseMapper<TokenResDto, TokenUIModel> {

    override fun toDTO(uiModel: TokenUIModel) = uiModel.run {
        TokenResDto(
            token = token
        )
    }

    override fun toUIModel(dto: TokenResDto) = dto.run {
        TokenUIModel(
            token = token
        )
    }
}
package uz.androbeck.virtualbank.domain.mapper.auth

import uz.androbeck.virtualbank.data.dto.request.UpdateTokenReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTokenMapper @Inject constructor() :
    BaseMapper<UpdateTokenReqDto, UpdateTokenReqUIModel> {
    override fun toUIModel(dto: UpdateTokenReqDto) = dto.run {
        UpdateTokenReqUIModel(
            refreshToken = refresh_token
        )
    }

    override fun toDTO(uiModel: UpdateTokenReqUIModel) = uiModel.run {
        UpdateTokenReqDto(
            refresh_token = refreshToken
        )
    }
}
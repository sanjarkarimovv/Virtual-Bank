package uz.androbeck.virtualbank.domain.mapper.auth.update_token

import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.UpdateTokenReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.UpdateTokenUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTokenMapper @Inject constructor() :
    BaseMapper<UpdateTokenReqDto, UpdateTokenReqUIModel> {
    override fun toUIModel(dto: UpdateTokenReqDto) = dto.run {
        UpdateTokenReqUIModel(
            refreshToken = refresh_token,
            accessToken = access_token
        )
    }

    override fun toDTO(uiModel: UpdateTokenReqUIModel) = uiModel.run {
        UpdateTokenReqDto(
            refresh_token = refreshToken,
            access_token = accessToken
        )
    }
}
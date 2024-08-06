package uz.androbeck.virtualbank.domain.mapper.auth.sign_in

import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import javax.inject.Inject

class SignInMapper @Inject constructor() : BaseMapper<SignInReqDto, SignInReqUIModel> {
    override fun toUIModel(dto: SignInReqDto) = dto.run {
        SignInReqUIModel(
            phone = dto.phone, password = dto.password
        )
    }

    override fun toDTO(uiModel: SignInReqUIModel) = uiModel.run {
        SignInReqDto(
            phone = uiModel.phone, password = uiModel.password
        )
    }
}
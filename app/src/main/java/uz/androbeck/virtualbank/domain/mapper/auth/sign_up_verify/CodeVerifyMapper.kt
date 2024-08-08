package uz.androbeck.virtualbank.domain.mapper.auth.sign_up_verify

import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CodeVerifyMapper @Inject constructor() :
    BaseMapper<CodeVerifyReqDto, CodeVerifyReqUIModel> {
    override fun toUIModel(dto: CodeVerifyReqDto) = dto.run {
        CodeVerifyReqUIModel(
            token = token,
            code = code
        )
    }

    override fun toDTO(uiModel: CodeVerifyReqUIModel) = uiModel.run {
        CodeVerifyReqDto(
            token = token,
            code = code
        )
    }
}
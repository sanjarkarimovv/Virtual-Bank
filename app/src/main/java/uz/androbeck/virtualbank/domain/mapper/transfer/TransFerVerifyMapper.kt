package uz.androbeck.virtualbank.domain.mapper.transfer

import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.domain.mapper.BaseMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import javax.inject.Inject

class TransferVerifyMapper @Inject constructor() :
    BaseMapper<CodeVerifyReqDto, CodeVerifyReqUIModel> {
    override fun toUIModel(dto: CodeVerifyReqDto): CodeVerifyReqUIModel = dto.run {
        CodeVerifyReqUIModel(
            token = token,
            code = code
        )
    }

    override fun toDTO(uiModel: CodeVerifyReqUIModel): CodeVerifyReqDto = uiModel.run {
        CodeVerifyReqDto(
            token = token,
            code = code
        )
    }
}
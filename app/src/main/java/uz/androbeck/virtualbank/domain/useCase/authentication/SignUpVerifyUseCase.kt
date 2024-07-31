package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.sign_up_verify_repository.SignUpVerifyRepository
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyTokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyTokenUIModel
import javax.inject.Inject

class SignUpVerifyUseCase @Inject constructor(
    private val signUpVerifyRepository: SignUpVerifyRepository,
    private val tokenMapper: SignUpVerifyTokenMapper,
    private val signUpVerifyMapper: SignUpVerifyMapper,
) {
    operator fun invoke(uiReqModel: SignUpVerifyReqUIModel): Flow<SignUpVerifyTokenUIModel> {
        val request = signUpVerifyMapper.toDTO(uiReqModel)
        return signUpVerifyRepository.signUpVerify(request).map { tokenMapper.toUIModel(it) }
    }
}
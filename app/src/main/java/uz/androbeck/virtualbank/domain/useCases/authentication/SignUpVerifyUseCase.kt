package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyTokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpVerifyReqUIModel
import javax.inject.Inject

class SignUpVerifyUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: SignUpVerifyTokenMapper,
    private val signUpVerifyMapper: SignUpVerifyMapper,
) {
    operator fun invoke(uiReqModel: SignUpVerifyReqUIModel) =
        authenticationRepository.signUpVerify(signUpVerifyMapper.toDTO(uiReqModel))
            .map { tokenMapper.toUIModel(it) }
}
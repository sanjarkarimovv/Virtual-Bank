package uz.androbeck.virtualbank.domain.useCase.authentication

import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignUpReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signUpMapper: SignUpMapper
) {
    suspend operator fun invoke(uiReqModel: SignUpReqUIModel): TokenUIModel {
        val request = signUpMapper.toDTO(uiReqModel)
        return tokenMapper.toUIModel(authenticationRepository.signUp(request))
    }
}
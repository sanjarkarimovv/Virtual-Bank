package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.TokenReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject

class SignUpResendUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signUpResendMapper: SignUpResendMapper
) {
    operator fun invoke(uiReqModel: TokenReqUIModel): Flow<TokenUIModel> {
        val request = signUpResendMapper.toDTO(uiReqModel)
        return authenticationRepository.signUpResend(request).map { tokenMapper.toUIModel(it) }
    }
}
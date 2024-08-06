package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.TokenReqUiModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject

class SignUpResendUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signUpResendMapper: SignUpResendMapper
) {
    operator fun invoke(uiReqModel: TokenReqUiModel): Flow<TokenUIModel> {
        val request = signUpResendMapper.toDTO(uiReqModel)
        return authenticationRepository.signUpResend(request).map { tokenMapper.toUIModel(it) }
    }
}
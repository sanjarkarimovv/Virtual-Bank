package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
    operator fun invoke(uiReqModel: SignUpReqUIModel): Flow<TokenUIModel> {
        val request = signUpMapper.toDTO(uiReqModel)
        return authenticationRepository.signUp(request).map { tokenMapper.toUIModel(it) }
    }
}
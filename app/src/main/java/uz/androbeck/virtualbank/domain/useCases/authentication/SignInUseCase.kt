package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.sign_in.SignInMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signInMapper: SignInMapper
) {
    operator fun invoke(uiReqModel: SignInReqUIModel) =
        authenticationRepository.signIn(signInMapper.toDTO(uiReqModel))
            .map { tokenMapper.toUIModel(it) }
}

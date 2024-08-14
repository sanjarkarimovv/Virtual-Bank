package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignInVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokensMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import uz.androbeck.virtualbank.ui.screens.Screen
import javax.inject.Inject

class AuthVerifyUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val signInVerifyMapper: SignInVerifyMapper,
    private val tokensMapper: TokensMapper
) {
    operator fun invoke(screen: Screen, codeVerifyReqUIModel: CodeVerifyReqUIModel) =
        when (screen) {
            Screen.LOGIN -> authenticationRepository.signInVerify(
                signInVerifyMapper.toDTO(
                    codeVerifyReqUIModel
                )
            ).map {
                tokensMapper.toUIModel(it)
            }

            Screen.REGISTRATION -> authenticationRepository.signUpVerify(
                signInVerifyMapper.toDTO(
                    codeVerifyReqUIModel
                )
            ).map {
                tokensMapper.toUIModel(it)
            }
        }
}
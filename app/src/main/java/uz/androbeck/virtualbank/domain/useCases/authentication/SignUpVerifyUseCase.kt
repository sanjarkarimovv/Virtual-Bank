package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.sign_up_verify.CodeVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokensMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import javax.inject.Inject

class SignUpVerifyUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokensMapper,
    private val codeVerifyMapper: CodeVerifyMapper,
) {
    operator fun invoke(uiReqModel: CodeVerifyReqUIModel) =
        authenticationRepository.signUpVerify(codeVerifyMapper.toDTO(uiReqModel))
            .map { tokenMapper.toUIModel(it) }
}
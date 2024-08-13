package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignInVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokensMapper
import uz.androbeck.virtualbank.domain.ui_models.common.CodeVerifyReqUIModel
import javax.inject.Inject

class SignInVerifyUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokensMapper: TokensMapper,
    private val signInVerifyMapper: SignInVerifyMapper
) {
    operator fun invoke(uiReqModel: CodeVerifyReqUIModel) =
        authenticationRepository.signInVerify(
            uiReqModel.let(signInVerifyMapper::toDTO)
        ).map { tokensMapper.toUIModel(it) }

}
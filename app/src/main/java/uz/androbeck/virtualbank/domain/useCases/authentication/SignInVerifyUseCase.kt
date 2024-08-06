package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignInVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInVerifyReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject

class SignInVerifyUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signInVerifyMapper: SignInVerifyMapper
) {
    operator fun invoke(uiReqModel: SignInVerifyReqUIModel) : Flow<TokenUIModel> {
        val request = signInVerifyMapper.toDTO(uiReqModel)
        return authenticationRepository.signInVerify(request).map { tokenMapper.toUIModel(it) }

    }

}
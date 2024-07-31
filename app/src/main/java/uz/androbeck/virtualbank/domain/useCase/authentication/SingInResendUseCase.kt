package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SingInResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SingInResendReqUiModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject

class SingInResendUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val singInResendMapper: SingInResendMapper
){
    operator fun invoke(uiReqModel: SingInResendReqUiModel) : Flow<TokenUIModel> {
        val request = singInResendMapper.toDTO(uiReqModel)
        return authenticationRepository.signInResend(request).map {
            tokenMapper.toUIModel(it)
        }
    }


}
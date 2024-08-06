package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SingInResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SingInResendReqUiModel
import javax.inject.Inject

class SingInResendUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val singInResendMapper: SingInResendMapper,
    private val tokenMapper: TokenMapper
) {
    operator fun invoke(uiReqModel: SingInResendReqUiModel) =
        authenticationRepository.singInResend(singInResendMapper.toDTO(uiReqModel))
            .map { tokenMapper.toUIModel(it) }
}
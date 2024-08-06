package uz.androbeck.virtualbank.domain.useCases.authentication

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.UpdateTokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.common.TokensMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokensMapper: TokensMapper,
    private val updateTokenMapper: UpdateTokenMapper
) {
    operator fun invoke(uiReqModel: UpdateTokenReqUIModel) =
        authenticationRepository.updateToken(updateTokenMapper.toDTO(uiReqModel))
            .map { tokensMapper.toUIModel(it) }
}
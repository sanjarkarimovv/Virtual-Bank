package uz.androbeck.virtualbank.domain.useCases.authentication

import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.TokensMapper
import uz.androbeck.virtualbank.domain.mapper.auth.UpdateTokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokensMapper: TokensMapper,
    private val updateTokenMapper: UpdateTokenMapper
) {
    suspend fun invoke(uiReqModel: UpdateTokenReqUIModel) =
        authenticationRepository.updateToken(updateTokenMapper.toDTO(uiReqModel)).run {
            tokensMapper.toUIModel(this)
        }
}
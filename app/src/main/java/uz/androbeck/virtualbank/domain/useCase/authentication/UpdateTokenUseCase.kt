package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.UpdateTokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateTokenUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val updateTokenMapper: UpdateTokenMapper
) {
//    operator fun invoke(uiReqModel: UpdateTokenReqUIModel): Flow<TokenUIModel> {
//        val request = updateTokenMapper.toDTO(uiReqModel)
//        return authenticationRepository.updateToken(request).map { tokenMapper.toUIModel(it) }
//    }
}
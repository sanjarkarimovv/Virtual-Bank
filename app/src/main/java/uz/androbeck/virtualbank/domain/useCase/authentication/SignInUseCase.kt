package uz.androbeck.virtualbank.domain.useCase.authentication

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SingInMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.ui_models.authentication.SignInReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.TokenUIModel
import javax.inject.Inject


class SignInUseCase @Inject constructor(
    private val authenticationRepository:AuthenticationRepository,
    private val tokenMapper: TokenMapper,
    private val signInMapper: SingInMapper
){
    operator fun invoke(uiReqModel:SignInReqUIModel):Flow<TokenUIModel>{
        val request=signInMapper.toDTO(uiReqModel)
        return authenticationRepository.signIn(request).map{ tokenMapper.toUIModel(it) }
    }
}
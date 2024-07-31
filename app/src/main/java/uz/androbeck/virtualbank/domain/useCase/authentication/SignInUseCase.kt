package uz.androbeck.virtualbank.domain.useCase.authentication

import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import javax.inject.Inject

class SignInUseCase @Inject  constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val tokenMapper: TokenMapper

){
}
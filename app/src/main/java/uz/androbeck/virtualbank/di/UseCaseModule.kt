package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpMapper
import uz.androbeck.virtualbank.domain.mapper.auth.SingInMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.useCase.authentication.SignInUseCase
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideSignUpUseCase(
        authenticationRepository: AuthenticationRepository,
        tokenMapper: TokenMapper,
        signUpMapper: SignUpMapper
    ) = SignUpUseCase(authenticationRepository, tokenMapper, signUpMapper)

    @Provides
    fun provideSignInUseCase(
        authenticationRepository: AuthenticationRepository,
        tokenMapper: TokenMapper,
        sigInMapper: SingInMapper
    )= SignInUseCase(authenticationRepository,tokenMapper,sigInMapper)


}
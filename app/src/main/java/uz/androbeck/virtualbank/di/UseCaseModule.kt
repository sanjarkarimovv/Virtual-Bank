package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.sign_up_verify_repository.SignUpVerifyRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.segn_up_verify.SignUpVerifyTokenMapper
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpUseCase
import uz.androbeck.virtualbank.domain.useCase.authentication.SignUpVerifyUseCase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideSignUpUseCase(
        authenticationRepository: AuthenticationRepository,
        tokenMapper: TokenMapper,
        signUpMapper: SignUpMapper,
    ) = SignUpUseCase(authenticationRepository, tokenMapper, signUpMapper)

    @Provides
    fun providesSignUpVerifyUseCase(
        signUpVerifyRepository: SignUpVerifyRepository,
        signUpVerifyTokenMapper: SignUpVerifyTokenMapper,
        signUpVerifyMapper: SignUpVerifyMapper,
    ): SignUpVerifyUseCase {
        return SignUpVerifyUseCase(
            signUpVerifyRepository = signUpVerifyRepository,
            tokenMapper = signUpVerifyTokenMapper,
            signUpVerifyMapper = signUpVerifyMapper
        )
    }
}
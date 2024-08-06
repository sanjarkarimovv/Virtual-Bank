package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpMapper
import uz.androbeck.virtualbank.domain.mapper.auth.SingInResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.sign_in.SignInMapper
import uz.androbeck.virtualbank.domain.mapper.home.FullInfoMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.mapper.home.UpdateInfoMapper
import uz.androbeck.virtualbank.domain.useCases.authentication.SignInUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SignUpUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SingInResendUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase

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
    fun provideSignInUseCase(
        authenticationRepository: AuthenticationRepository,
        tokenMapper: TokenMapper,
        signInMapper: SignInMapper,
    ) = SignInUseCase(authenticationRepository, tokenMapper, signInMapper)

    @Provides
    fun provideGetFullInfoUseCase(
        homeRepository: HomeRepository,
        fullInfoMapper: FullInfoMapper
    ) = GetFullInfoUseCase(homeRepository, fullInfoMapper)

    @Provides
    fun provideUpdateInfoUseCase(
        homeRepository: HomeRepository,
        updateInfoMapper: UpdateInfoMapper,
        messageMapper: MessageMapper
    ) = PutUpdateInfoUseCase(homeRepository, updateInfoMapper, messageMapper)

    @Provides
    fun provideSignInResendUseCase(
        authenticationRepository: AuthenticationRepository,
        signInResendMapper: SingInResendMapper,
        tokenMapper: TokenMapper,
    ) = SingInResendUseCase(authenticationRepository, signInResendMapper, tokenMapper)
}
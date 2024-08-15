package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.androbeck.virtualbank.data.repository.authentication.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.data.repository.history.HistoryRepository
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.auth.SignInVerifyMapper
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpMapper
import uz.androbeck.virtualbank.domain.mapper.auth.SignUpResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.SingInResendMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.TokensMapper
import uz.androbeck.virtualbank.domain.mapper.auth.UpdateTokenMapper
import uz.androbeck.virtualbank.domain.mapper.auth.sign_in.SignInMapper
import uz.androbeck.virtualbank.domain.mapper.card.AddCardMapper
import uz.androbeck.virtualbank.domain.mapper.card.DeleteCardMapper
import uz.androbeck.virtualbank.domain.mapper.history.GetHistoryMapper
import uz.androbeck.virtualbank.domain.mapper.history.TransfersMapper
import uz.androbeck.virtualbank.domain.mapper.home.ComponentsMapper
import uz.androbeck.virtualbank.domain.mapper.home.FullInfoMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.mapper.home.TotalBalanceMapper
import uz.androbeck.virtualbank.domain.mapper.home.UpdateInfoMapper
import uz.androbeck.virtualbank.domain.useCases.authentication.AuthVerifyUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SignInUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SignUpResendUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SignUpUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.SingInResendUseCase
import uz.androbeck.virtualbank.domain.useCases.authentication.UpdateTokenUseCase
import uz.androbeck.virtualbank.domain.useCases.card.AddCardUseCase
import uz.androbeck.virtualbank.domain.useCases.card.DeleteCardUseCase
import uz.androbeck.virtualbank.domain.useCases.history.GetHistoryUseCase
import uz.androbeck.virtualbank.domain.useCases.history.LastTransfersUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetComponentsFromCacheUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.GetTotalBalanceUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutComponentsUseCase
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.home.UpdateComponentsInCatchUseCase

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
    fun provideUpdateTokenUseCase(
        authenticationRepository: AuthenticationRepository,
        tokensMapper: TokensMapper,
        updateTokenMapper: UpdateTokenMapper
    ) = UpdateTokenUseCase(authenticationRepository, tokensMapper, updateTokenMapper)


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
    fun provideGetTotalBalanceUseCase(
        homeRepository: HomeRepository,
        totalBalanceMapper: TotalBalanceMapper
    ) = GetTotalBalanceUseCase(homeRepository, totalBalanceMapper)

    @Provides
    fun provideUpdateInfoUseCase(
        homeRepository: HomeRepository,
        updateInfoMapper: UpdateInfoMapper,
        messageMapper: MessageMapper
    ) = PutUpdateInfoUseCase(homeRepository, updateInfoMapper, messageMapper)

    @Provides
    fun provideLastTransfersUseCase(
        historyRepository: HistoryRepository,
        transfersMapper: TransfersMapper,
    ) = LastTransfersUseCase(historyRepository, transfersMapper)

    @Provides
    fun provideGetHistoryUseCase(
        historyRepository: HistoryRepository,
        getHistoryMapper: GetHistoryMapper,
    ) = GetHistoryUseCase(historyRepository,getHistoryMapper)

    @Provides
    fun provideSignInResendUseCase(
        authenticationRepository: AuthenticationRepository,
        signInResendMapper: SingInResendMapper,
        tokenMapper: TokenMapper,
    ) = SingInResendUseCase(authenticationRepository, signInResendMapper, tokenMapper)

    @Provides
    fun provideSignUpResendUseCase(
        authenticationRepository: AuthenticationRepository,
        signUpResendMapper: SignUpResendMapper,
        tokenMapper: TokenMapper,
    ) = SignUpResendUseCase(authenticationRepository, tokenMapper, signUpResendMapper)

    @Provides
    fun provideAuthVerifyUseCase(
        authenticationRepository: AuthenticationRepository,
        signInVerifyMapper: SignInVerifyMapper,
        tokensMapper: TokensMapper
    ) = AuthVerifyUseCase(authenticationRepository, signInVerifyMapper, tokensMapper)

    @Provides
    fun provideGetComponentsFromCacheUseCase(
        homeRepository: HomeRepository,
        componentsMapper: ComponentsMapper
    ) = GetComponentsFromCacheUseCase(homeRepository, componentsMapper)

    @Provides
    fun providePutComponentsUseCase(
        homeRepository: HomeRepository,
        mapper: ComponentsMapper
    ) = PutComponentsUseCase(homeRepository, mapper)

    @Provides
    fun provideUpdateUseCase(
        homeRepository: HomeRepository,
        mapper: ComponentsMapper
    ) = UpdateComponentsInCatchUseCase(
        homeRepository
    )

    @Provides
    fun provideDeleteCardUseCase(
        cardRepository: CardRepository,
        deleteCardMapper: DeleteCardMapper
    ) = DeleteCardUseCase(cardRepository, deleteCardMapper)
    @Provides
    fun provideAddCardUseCase(
        cardRepository: CardRepository,
        addCardMapper: AddCardMapper,
        messageMapper: MessageMapper
    )= AddCardUseCase(cardRepository,addCardMapper,messageMapper)

}
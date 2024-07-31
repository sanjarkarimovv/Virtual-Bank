package uz.androbeck.virtualbank.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.androbeck.virtualbank.data.repository.AuthenticationRepository
import uz.androbeck.virtualbank.data.repository.AuthenticationRepositoryImpl
import uz.androbeck.virtualbank.data.repository.sign_up_verify_repository.SignUpVerifyRepository
import uz.androbeck.virtualbank.data.repository.sign_up_verify_repository.SignUpVerifyRepositoryImpl
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote.SignUpVerifyRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        authenticationRemoteDataSource: AuthenticationRemoteDataSource
    ): AuthenticationRepository {
        return AuthenticationRepositoryImpl(authenticationRemoteDataSource)
    }
    @Provides
    @Singleton
    fun provideSignUpVerifyRepository(
        signUpVerifyRemoteDataSource: SignUpVerifyRemoteDataSource
    ):SignUpVerifyRepository{
        return SignUpVerifyRepositoryImpl(signUpVerifyRemoteDataSource)
    }

}
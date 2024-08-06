package uz.androbeck.virtualbank.data.repository.authentication

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override fun signUp(request: SignUpReqDto) =
        remoteDataSource.signUp(request)

    override fun signUpVerify(request: SignUpVerifyReqDto) =
        remoteDataSource.signUpVerify(request)

    override fun signIn(request: SignInReqDto)=
        remoteDataSource.signIn(request)

    override fun singInResend(request: TokenReqDto) =
        remoteDataSource.signInResend(request)

    override fun signUpResend(request: TokenReqDto) =
        remoteDataSource.signUpResend(request)
}
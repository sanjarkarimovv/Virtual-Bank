package uz.androbeck.virtualbank.data.repository.authentication

import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.request.TokenReqDto
import uz.androbeck.virtualbank.data.dto.request.auth.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_up.SignUpReqDto
import uz.androbeck.virtualbank.data.source.remote.auth.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override fun signUp(request: SignUpReqDto) =
        remoteDataSource.signUp(request)

    override fun signUpVerify(request: CodeVerifyReqDto) =
        remoteDataSource.signUpVerify(request)

    override fun updateToken(request: UpdateTokenReqDto) =
        remoteDataSource.updateToken(request)

    override fun signIn(request: SignInReqDto) =
        remoteDataSource.signIn(request)

    override fun singInResend(request: TokenReqDto) =
        remoteDataSource.signInResend(request)

    override fun signUpResend(request: TokenReqDto) =
        remoteDataSource.signUpResend(request)

    override fun signInVerify(request: CodeVerifyReqDto) =
        remoteDataSource.signInVerify(request)
}
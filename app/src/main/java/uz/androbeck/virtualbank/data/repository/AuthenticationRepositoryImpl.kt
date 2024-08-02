package uz.androbeck.virtualbank.data.repository

import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in_request.SignInReqDto
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override fun signUp(request: SignUpReqDto) =
        remoteDataSource.signUp(request)

    override fun signUpVerify(request: SignUpVerifyReqDto)=
        remoteDataSource.signUpVerify(request)

    override fun signIn(request: SignInReqDto)=
        remoteDataSource.signIn(request)
}
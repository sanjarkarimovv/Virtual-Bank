package uz.androbeck.virtualbank.data.repository.authentication

import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.source.remote.AuthenticationRemoteDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteDataSource: AuthenticationRemoteDataSource
) : AuthenticationRepository {
    override fun signUp(request: SignUpReqDto) =
        remoteDataSource.signUp(request)

    override fun signUpVerify(request: SignUpVerifyReqDto)=
        remoteDataSource.signUpVerify(request)

}
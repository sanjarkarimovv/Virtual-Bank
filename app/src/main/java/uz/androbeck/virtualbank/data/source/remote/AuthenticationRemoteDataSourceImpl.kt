package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationRemoteDataSource {
    override fun signUp(request: SignUpReqDto) = flow {
        emit(service.signUp(request))
    }

    override fun signUpVerify(request: SignUpVerifyReqDto) = flow {
        emit(service.signUpVerify(request))
    }

    override fun updateToken(request: UpdateTokenReqDto)= flow{
        emit(service.updateToken(request))
    }

    override fun signIn(request: SignInReqDto) = flow {
        emit(service.signIn(request))
    }

    override fun signInResend(request: TokenReqDto) = flow {
        emit(service.signInResend(request))
    }

    override fun signUpResend(request: TokenReqDto) = flow {
        emit(service.signUpResend(request))
    }
}
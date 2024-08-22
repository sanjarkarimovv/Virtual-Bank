package uz.androbeck.virtualbank.data.source.remote.auth

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.request.TokenReqDto
import uz.androbeck.virtualbank.data.dto.request.auth.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_up.SignUpReqDto
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationRemoteDataSource {
    override fun signUp(request: SignUpReqDto) = flow {
        emit(service.signUp(request))
    }

    override fun signInVerify(request: CodeVerifyReqDto) = flow {
        emit(service.signInVerify(request))
    }

    override fun signUpVerify(request: CodeVerifyReqDto) = flow {
        emit(service.signUpVerify(request))
    }

    override suspend fun updateToken(request: UpdateTokenReqDto) = service.updateToken(request)

    override fun signIn(reqDto: SignInReqDto) = flow {
        emit(service.signIn(reqDto))
    }

    override fun signInResend(request: TokenReqDto) = flow {
        emit(service.signInResend(request))
    }

    override fun signUpResend(request: TokenReqDto) = flow {
        emit(service.signUpResend(request))
    }
}
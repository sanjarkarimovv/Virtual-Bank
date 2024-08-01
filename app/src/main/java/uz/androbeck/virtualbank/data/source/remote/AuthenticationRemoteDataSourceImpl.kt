package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.dto.request.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import javax.inject.Inject

class  AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationRemoteDataSource {
    override fun signUp(request: SignUpReqDto) = flow {
        emit(service.signUp(request))
    }

    override fun signIn(request: SignInReqDto) =flow {
        emit(service.signIn(request))
    }


}
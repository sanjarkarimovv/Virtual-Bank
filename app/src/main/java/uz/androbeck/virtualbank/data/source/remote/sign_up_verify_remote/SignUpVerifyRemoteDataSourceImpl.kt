package uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote

import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.api.SignUpVerifyService
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import javax.inject.Inject

class SignUpVerifyRemoteDataSourceImpl @Inject constructor(
    private val service: SignUpVerifyService,
) : SignUpVerifyRemoteDataSource {
    override fun signUpVerify(request: SignUpVerifyReqDto) = flow {
        emit(service.signUpVerify(request))
    }
}
package uz.androbeck.virtualbank.data.source.remote.sign_up_verify_remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response.SuccessResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto

interface SignUpVerifyRemoteDataSource {
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<SuccessResDto>
}
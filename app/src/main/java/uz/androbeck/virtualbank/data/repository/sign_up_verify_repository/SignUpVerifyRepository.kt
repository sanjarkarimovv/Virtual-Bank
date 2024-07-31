package uz.androbeck.virtualbank.data.repository.sign_up_verify_repository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response.SuccessResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto

interface SignUpVerifyRepository {
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<SuccessResDto>
}
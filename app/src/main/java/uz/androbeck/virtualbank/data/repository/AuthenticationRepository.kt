package uz.androbeck.virtualbank.data.repository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignInVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto

interface AuthenticationRepository {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signInVerify(request: SignInVerifyReqDto): Flow<TokenResDto>

    fun signUpVerify(request: SignUpVerifyReqDto): Flow<TokensResDto>

}
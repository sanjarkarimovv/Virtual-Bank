package uz.androbeck.virtualbank.data.repository.authentication

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto

interface AuthenticationRepository {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<TokensResDto>

}
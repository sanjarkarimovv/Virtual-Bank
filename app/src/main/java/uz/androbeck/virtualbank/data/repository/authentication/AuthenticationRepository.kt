package uz.androbeck.virtualbank.data.repository.authentication

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in_request.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto

interface AuthenticationRepository {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<TokensResDto>
    fun signIn(request: SignInReqDto): Flow<TokenResDto>
    fun singInResend(request: TokenReqDto): Flow<TokenResDto>
}
package uz.androbeck.virtualbank.data.repository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto

interface AuthenticationRepository {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<TokensResDto>
    fun updateToken(request: UpdateTokenReqDto): Flow<TokensResDto>
    fun signIn(request: SignInReqDto): Flow<TokenResDto>
    fun singInResend(request: TokenReqDto): Flow<TokenResDto>
    fun signUpResend(request: TokenReqDto) : Flow<TokenResDto>
}
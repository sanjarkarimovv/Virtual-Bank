package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto

interface AuthenticationRemoteDataSource {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signUpVerify(request: SignUpVerifyReqDto): Flow<TokensResDto>
    fun signIn(reqDto: SignInReqDto):Flow<TokenResDto>
    fun signInResend(request: TokenReqDto): Flow<TokenResDto>
    fun signUpResend(request: TokenReqDto) : Flow<TokenResDto>
}



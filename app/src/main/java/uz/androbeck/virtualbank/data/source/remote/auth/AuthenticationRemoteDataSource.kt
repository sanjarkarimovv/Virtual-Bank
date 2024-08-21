package uz.androbeck.virtualbank.data.source.remote.auth

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.common.request.TokenReqDto
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.auth.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_up.SignUpReqDto

interface AuthenticationRemoteDataSource {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>

    fun signInVerify(request: CodeVerifyReqDto): Flow<TokensResDto>
    fun signUpVerify(request: CodeVerifyReqDto): Flow<TokensResDto>
    suspend fun updateToken(request: UpdateTokenReqDto): TokensResDto
    fun signIn(reqDto: SignInReqDto): Flow<TokenResDto>
    fun signInResend(request: TokenReqDto): Flow<TokenResDto>
    fun signUpResend(request: TokenReqDto): Flow<TokenResDto>
}



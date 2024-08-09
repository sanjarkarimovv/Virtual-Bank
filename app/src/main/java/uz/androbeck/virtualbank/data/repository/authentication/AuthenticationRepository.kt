package uz.androbeck.virtualbank.data.repository.authentication

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.sign_up.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.common.request.CodeVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.auth.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.common.request.TokenReqDto

interface AuthenticationRepository {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
    fun signUpVerify(request: CodeVerifyReqDto): Flow<TokensResDto>
    fun updateToken(request: UpdateTokenReqDto): Flow<TokensResDto>
    fun signIn(request: SignInReqDto): Flow<TokenResDto>
    fun singInResend(request: TokenReqDto): Flow<TokenResDto>
    fun signUpResend(request: TokenReqDto) : Flow<TokenResDto>
    fun signInVerify(request: CodeVerifyReqDto): Flow<TokensResDto>
}
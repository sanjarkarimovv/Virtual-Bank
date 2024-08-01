package uz.androbeck.virtualbank.data.repository

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto

interface AuthenticationRepository {
     fun signUp(request: SignUpReqDto): Flow<TokenResDto>
     fun signIn(request: SignInReqDto): Flow<TokenResDto>

}
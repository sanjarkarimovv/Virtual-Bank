package uz.androbeck.virtualbank.data.repository

import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto

interface AuthenticationRepository {
    suspend fun signUp(request: SignUpReqDto): TokenResDto
}
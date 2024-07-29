package uz.androbeck.virtualbank.data.source.remote

import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto

interface AuthenticationRemoteDataSource {
    suspend fun signUp(request: SignUpReqDto): TokenResDto
}



package uz.androbeck.virtualbank.data.source.remote

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto

interface AuthenticationRemoteDataSource {
    fun signUp(request: SignUpReqDto): Flow<TokenResDto>
}



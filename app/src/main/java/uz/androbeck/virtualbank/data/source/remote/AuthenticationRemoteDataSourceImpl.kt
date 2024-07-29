package uz.androbeck.virtualbank.data.source.remote

import uz.androbeck.virtualbank.data.api.AuthenticationService
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import javax.inject.Inject

class AuthenticationRemoteDataSourceImpl @Inject constructor(
    private val service: AuthenticationService
) : AuthenticationRemoteDataSource {
    override suspend fun signUp(request: SignUpReqDto): TokenResDto {
        val response = service.signUp(request)
        if (response.isSuccessful) {
            return response.body() ?: TokenResDto()
        }
        return TokenResDto()
    }
}
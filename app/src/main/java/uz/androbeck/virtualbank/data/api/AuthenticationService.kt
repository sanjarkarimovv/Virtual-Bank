package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.request.SignInVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.utils.Constants

interface AuthenticationService {
    @POST(Constants.Endpoint.SIGN_UP)
    suspend fun signUp(
        @Body request: SignUpReqDto
    ): TokenResDto

    @POST(Constants.Endpoint.SIGN_IN_VERIFY)
    suspend fun signInVerify(
        @Body request: SignInVerifyReqDto
    ): TokenResDto

}
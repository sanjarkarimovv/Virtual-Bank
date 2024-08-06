package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.TokenResDto
import uz.androbeck.virtualbank.data.dto.common.response.TokensResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpReqDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.data.dto.request.UpdateTokenReqDto
import uz.androbeck.virtualbank.data.dto.request.sign_in.SignInReqDto
import uz.androbeck.virtualbank.data.dto.request.TokenReqDto
import uz.androbeck.virtualbank.utils.Constants

interface AuthenticationService {
    @POST(Constants.Endpoint.SIGN_UP)
    suspend fun signUp(
        @Body request: SignUpReqDto
    ): TokenResDto

    @POST(Constants.Endpoint.SIGN_UP_VERIFY)
    suspend fun signUpVerify(
        @Body request: SignUpVerifyReqDto
    ): TokensResDto

    @POST(Constants.Endpoint.UPDATE_TOKEN)
    suspend fun updateToken(
        @Body request: UpdateTokenReqDto
    ): TokensResDto

    @POST(Constants.Endpoint.SIGN_IN)
    suspend fun signIn(
        @Body request: SignInReqDto
    ): TokenResDto

    @POST(Constants.Endpoint.SIGN_IN_RESEND)
    suspend fun signInResend(
        @Body request: TokenReqDto
    ): TokenResDto

}
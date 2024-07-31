package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import uz.androbeck.virtualbank.data.dto.common.response.sing_up_verify_response.SuccessResDto
import uz.androbeck.virtualbank.data.dto.request.SignUpVerifyReqDto
import uz.androbeck.virtualbank.utils.Constants

interface SignUpVerifyService {
    @POST(Constants.Endpoint.SIGN_UP_VERIFY)
    suspend fun signUpVerify(
        @Body request: SignUpVerifyReqDto
    ): SuccessResDto
}
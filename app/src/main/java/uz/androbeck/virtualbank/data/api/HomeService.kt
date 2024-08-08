package uz.androbeck.virtualbank.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.response.home.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.response.home.FullInfoDto
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.utils.Constants

interface HomeService {
    @GET(Constants.Endpoint.FULL_INFO)
    suspend fun getFullInfo(): FullInfoDto

    @GET(Constants.Endpoint.BASIC_INFO)
    suspend fun getBasicInfo(): BasicInfoResDto

    @PUT(Constants.Endpoint.UPDATE_INFO)
    suspend fun putUpdateInfo(
        @Body request: UpdateInfoReqDto
    ): MessageResDto

}
package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import uz.androbeck.virtualbank.data.dto.common.response.home_response.BasicInfoResDto
import uz.androbeck.virtualbank.data.dto.request.FullInfoDto
import uz.androbeck.virtualbank.utils.Constants

interface HomeService {
    @GET(Constants.Endpoint.FULL_INFO)
    suspend fun getFullInfo(): FullInfoDto

    @GET(Constants.Endpoint.BASIC_INFO)
    suspend fun getBasicInfo(): BasicInfoResDto

}
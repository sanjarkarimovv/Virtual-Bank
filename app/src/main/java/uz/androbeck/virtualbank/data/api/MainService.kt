package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.utils.Constants

interface MainService {

    @GET(Constants.Endpoint.FULL_INFO)
    suspend fun getFullInfo(@Header("Authorization") authToken: String?): MainFullInfoDto
}
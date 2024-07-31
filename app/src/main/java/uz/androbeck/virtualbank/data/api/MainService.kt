package uz.androbeck.virtualbank.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.utils.Constants

const val TOKEN = "= \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJodHRwOi8vMTQzLjE5OC40OC4yMjI6ODQvdjEvbW9iaWxlLWJhbmsvYXV0aCIsImFjY291bnQtaWQiOiIyIiwiaXNzIjoiaHR0cDovLzE0My4xOTguNDguMjIyOjg0L3YxL21vYmlsZS1iYW5rIiwiZXhwIjoxNjcxMTA4NDY2fQ.zbGNA_qRQsoLFJQW3Lh2nFZrN_lVlDcpLLg7rrWmKWs\""
interface MainService {

    @GET(Constants.Endpoint.FULL_INFO)
    suspend fun getFullInfo(@Header("Authorization") token: String = "Bearer $TOKEN"): MainFullInfoDto
}
package uz.androbeck.virtualbank.data.remote.service

import retrofit2.http.GET
import retrofit2.http.Path
import uz.androbeck.virtualbank.data.remote.dto.UserModel

interface ApiService {
    // example
    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: String):UserModel

}
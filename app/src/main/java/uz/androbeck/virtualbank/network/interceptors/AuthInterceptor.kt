package uz.androbeck.virtualbank.network.interceptors

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import uz.androbeck.virtualbank.BuildConfig
import uz.androbeck.virtualbank.data.dto.common.response.Tokens2ResDto
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        if (response.code == 401) {
            val refreshToken = preferencesProvider.refreshToken
            val newAccessToken = runBlocking {
                val api = originalRequest.newBuilder()
                    .post("{\"refresh-token\":\"$refreshToken\"}".toRequestBody("application/json".toMediaType()))
                    .url(BuildConfig.BASE_URL + "auth/update-token").build()
                val updateTokenResponse = runBlocking {
                    chain.proceed(api).body?.string()
                }
                val dto = Gson().fromJson(
                    updateTokenResponse, Tokens2ResDto::class.java
                )
                dto.access_token?.let {
                    preferencesProvider.accessToken = it
                }
                dto.refresh_token?.let {
                    preferencesProvider.refreshToken = it
                }
                dto.access_token
            }
            if (newAccessToken != null) {
                val newRequest = originalRequest.newBuilder().build()
                return chain.proceed(newRequest)
            }
        }
        return response
    }
}
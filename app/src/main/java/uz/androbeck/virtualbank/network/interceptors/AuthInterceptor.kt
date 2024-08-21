package uz.androbeck.virtualbank.network.interceptors

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import uz.androbeck.virtualbank.domain.ui_models.authentication.UpdateTokenReqUIModel
import uz.androbeck.virtualbank.domain.useCases.authentication.UpdateTokenUseCase
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val preferencesProvider: PreferencesProvider
) : Interceptor {

    @Inject
    lateinit var  updateTokenUseCase: UpdateTokenUseCase
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest)
        val accessToken = preferencesProvider.token
        if (response.code == 401) {
            val refreshToken = preferencesProvider.refreshToken
            val refreshedToken = runBlocking {
                val updateTokenResponse =
                    updateTokenUseCase.invoke(UpdateTokenReqUIModel(refreshToken))
                updateTokenResponse.accessToken?.let {
                    preferencesProvider.token = it
                }
                updateTokenResponse.refreshToken?.let {
                    preferencesProvider.refreshToken = it
                }
                updateTokenResponse.refreshToken
            }
            if (refreshedToken != null) {
                val newRequest =
                    originalRequest.newBuilder().header("Authorization", "Bearer $refreshedToken")
                        .build()
                return chain.proceed(newRequest)
            }
        }
        // Add the access token to the request header
        val authorizedRequest =
            originalRequest.newBuilder().header("Authorization", "Bearer $accessToken").build()

        return response
    }
}
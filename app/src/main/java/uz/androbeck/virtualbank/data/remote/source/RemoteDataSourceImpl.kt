package uz.androbeck.virtualbank.data.remote.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import uz.androbeck.virtualbank.data.local.entity.UserEntity
import uz.androbeck.virtualbank.data.remote.dto.UserModel
import uz.androbeck.virtualbank.data.remote.service.ApiService

class RemoteDataSourceImpl(
    private val retrofit: Retrofit
) : RemoteDataSource {
    override suspend fun getUser(id: String): Flow<UserModel> {

        return flow {
            retrofit.create(ApiService::class.java).getUser(id)
        }
    }
}
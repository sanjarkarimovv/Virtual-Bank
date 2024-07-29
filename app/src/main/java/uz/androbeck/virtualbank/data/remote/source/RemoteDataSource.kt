package uz.androbeck.virtualbank.data.remote.source

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.local.entity.UserEntity
import uz.androbeck.virtualbank.data.remote.dto.UserModel

interface RemoteDataSource {
    suspend fun getUser(id: String): Flow<UserModel>
}
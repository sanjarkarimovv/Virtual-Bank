package uz.androbeck.virtualbank.data.local.source

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.remote.dto.UserModel

interface LocalDataSource {
    suspend fun getUserForRoom(id: String): Flow<UserModel>
}
package uz.androbeck.virtualbank.data.local.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.androbeck.virtualbank.data.local.dao.UserDao
import uz.androbeck.virtualbank.data.remote.dto.UserModel

class LocalDataSourceImpl(
    private val userDao: UserDao
) : LocalDataSource {
    override suspend fun getUserForRoom(id: String): Flow<UserModel> {
        return flow {
            //
        }
    }
}
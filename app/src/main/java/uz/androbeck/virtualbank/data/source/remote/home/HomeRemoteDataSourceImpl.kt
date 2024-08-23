package uz.androbeck.virtualbank.data.source.remote.home

import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.data.api.HomeService
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import uz.androbeck.virtualbank.data.dto.response.home.fireBaseResDto.GetTvBannerResEvent
import uz.androbeck.virtualbank.domain.ui_models.home.AdvertisingModel
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRemoteDataSource {

    override fun getFullInfo() = flow {
        emit(homeService.getFullInfo())
    }

    override fun getBasicInfo() = flow {
        emit(homeService.getBasicInfo())
    }

    override fun putUpdateInfo(request: UpdateInfoReqDto) = flow {
        emit(homeService.putUpdateInfo(request))
    }

    override fun getTotalBalance()= flow {
        emit(homeService.getTotalBalance())
    }

    override fun getTvBannersFromFirebaseStorage(): Flow<GetTvBannerResEvent> {
        val flow = MutableStateFlow<GetTvBannerResEvent>(GetTvBannerResEvent.Loading)
        CoroutineScope(Dispatchers.IO).launch {
            val list = mutableListOf<AdvertisingModel>()
            Firebase.storage.reference.child("image").listAll().addOnSuccessListener { listRes ->
                listRes.items.forEach { item ->

                    item.downloadUrl.addOnSuccessListener { uri ->
                        val advertisingModel = AdvertisingModel(uri = uri.toString())
                        list.add(advertisingModel)
                        println(uri.toString())
                        flow.tryEmit(GetTvBannerResEvent.Success(list.toList()))
                    }
                }
            }.addOnFailureListener { exception ->
                flow.tryEmit(GetTvBannerResEvent.Error(exception.message.toString()))
            }
        }
        return flow
    }
}
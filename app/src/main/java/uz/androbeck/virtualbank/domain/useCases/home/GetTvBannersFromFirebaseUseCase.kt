package uz.androbeck.virtualbank.domain.useCases.home

import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTvBannersFromFirebaseUseCase @Inject constructor(
    private val repository: HomeRepository,
) {
    fun getTvBannerFromFireBase() = repository.getTvBannerFromFirebase()
}
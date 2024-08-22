package uz.androbeck.virtualbank.domain.useCases.home

import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import javax.inject.Inject

class UpdateComponentsInCatchUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) {
    suspend fun updateComponents(id: Int, isVisibility: Boolean) {
        homeRepository.updateComponents(id, isVisibility)
    }
}
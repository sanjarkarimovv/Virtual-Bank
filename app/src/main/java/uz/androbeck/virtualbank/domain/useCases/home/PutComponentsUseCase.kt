package uz.androbeck.virtualbank.domain.useCases.home

import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.ComponentsMapper
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import javax.inject.Inject
class PutComponentsUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val mapper: ComponentsMapper
) {
    suspend fun putComponents(uiComponents: UiComponents) {
        homeRepository.putComponents(mapper.toDTO(uiComponents))
    }


}


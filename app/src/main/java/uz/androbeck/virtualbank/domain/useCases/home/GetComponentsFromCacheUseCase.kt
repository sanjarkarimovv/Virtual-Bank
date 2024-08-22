package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.ComponentsMapper
import javax.inject.Inject

class GetComponentsFromCacheUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val uiComponentsMapper: ComponentsMapper
) {
    operator fun invoke() = homeRepository.getAllHomeComponents()
        .map { it.map { dto -> uiComponentsMapper.toUIModel(dto) } }
}
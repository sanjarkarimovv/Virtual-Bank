package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.FullInfoMapper
import javax.inject.Inject

class GetFullInfoUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val fullInfoMapper: FullInfoMapper
) {
    operator fun invoke() =
        repository.getFullInfo().map {
            fullInfoMapper.toUIModel(it)
        }
}
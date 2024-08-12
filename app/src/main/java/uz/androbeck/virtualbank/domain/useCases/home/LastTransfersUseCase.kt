package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.LastTransfersMapper
import javax.inject.Inject

class LastTransfersUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val lastTransfersMapper: LastTransfersMapper
) {
    operator fun invoke() =
        homeRepository.getLastTransfers().map {
            lastTransfersMapper.toUIModel(it)
        }

}
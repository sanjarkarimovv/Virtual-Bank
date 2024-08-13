package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.TotalBalanceMapper
import javax.inject.Inject

class GetTotalBalanceUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val totalBalanceMapper: TotalBalanceMapper,
) {
    operator fun invoke() =
        homeRepository.getTotalBalance().map {
            totalBalanceMapper.toUIModel(it)
        }
}
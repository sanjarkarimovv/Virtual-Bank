package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.BasicInfoMapper
import javax.inject.Inject

class BasicInfoUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val basicInfoMapper: BasicInfoMapper
) {
    operator fun invoke() = repository.getBasicInfo().map {
        basicInfoMapper.toUIModel(it)
    }
}
package uz.androbeck.virtualbank.domain.useCases.main

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.mainRepository.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.main.FullInfoMapper
import uz.androbeck.virtualbank.domain.ui_models.main.FullInfoUIModel
import javax.inject.Inject

class GetFullInfoUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val fullInfoMapper: FullInfoMapper
) {
    operator fun invoke(token: String?): kotlinx.coroutines.flow.Flow<FullInfoUIModel> {
        return repository.getFullInfo(token).map {
            fullInfoMapper.toUIModel(it)
        }
    }
}
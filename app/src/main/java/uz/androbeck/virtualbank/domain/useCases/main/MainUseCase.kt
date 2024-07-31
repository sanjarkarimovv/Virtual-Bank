package uz.androbeck.virtualbank.domain.useCases.main

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.data.repository.mainRepository.MainRepository
import uz.androbeck.virtualbank.domain.mapper.main.GetFullInfoMapper
import uz.androbeck.virtualbank.domain.ui_models.main.FullInfoUIModel
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val repository: MainRepository,
    private val getFullInfoMapper: GetFullInfoMapper
) {
    operator fun invoke(token: String?): kotlinx.coroutines.flow.Flow<FullInfoUIModel> {
        return repository.getFullInfo(token).map {
            getFullInfoMapper.toUIModel(it)
        }
    }
}
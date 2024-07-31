package uz.androbeck.virtualbank.domain.useCases.main

import uz.androbeck.virtualbank.data.dto.request.MainFullInfoDto
import uz.androbeck.virtualbank.data.repository.mainRepository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): kotlinx.coroutines.flow.Flow<MainFullInfoDto> {
        return repository.getFullInfo()
    }
}
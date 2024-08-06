package uz.androbeck.virtualbank.domain.useCases.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.home.HomeRepository
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.mapper.home.UpdateInfoMapper
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import uz.androbeck.virtualbank.domain.ui_models.home.UpdateInfoUIModel
import javax.inject.Inject

class PutUpdateInfoUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
    private val updateInfoMapper: UpdateInfoMapper,
    private val messageMapper: MessageMapper
) {
    operator fun invoke(uiReqModel: UpdateInfoUIModel): Flow<MessageUIModel> {
        val request = updateInfoMapper.toDTO(uiReqModel)
        return homeRepository.putUpdateInfo(request).map { messageMapper.toUIModel(it) }
    }
}
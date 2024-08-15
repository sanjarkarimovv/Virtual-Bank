package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.UpdateCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.card.UpdateCardUIModel
import javax.inject.Inject


@Singleton
class PutUpdateCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val updateCardMapper: UpdateCardMapper,
    private val messageMapper: MessageMapper
) {
    suspend operator fun invoke(uiReqModel: UpdateCardUIModel) : Flow<MessageMapper> {
        val request = updateCardMapper.toDTO(uiReqModel)
        return cardRepository.updateCard(request).map { messageMapper.toUIModel(it) }
    }
}
/**
 * operator fun invoke(uiReqModel: UpdateInfoUIModel): Flow<MessageUIModel> {
 *         val request = updateInfoMapper.toDTO(uiReqModel)
 *         return homeRepository.putUpdateInfo(request).map { messageMapper.toUIModel(it) }
 *     }
 */
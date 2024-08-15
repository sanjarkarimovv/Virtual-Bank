package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.UpdateCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.card.UpdateCardUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PutUpdateCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val updateCardMapper: UpdateCardMapper,
    private val messageMapper: MessageMapper
) {
    operator fun invoke(uiReqModel: UpdateCardUIModel): Flow<MessageUIModel> {
        val request = updateCardMapper.toDTO(uiReqModel)
        return cardRepository.putUpdateCard(request).map { messageMapper.toUIModel(it) }
    }
}
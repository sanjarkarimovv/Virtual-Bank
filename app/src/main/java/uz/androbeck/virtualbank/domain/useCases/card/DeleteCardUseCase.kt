package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.DeleteCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.card.DeleteCardReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val messageMapper: MessageMapper,
) {
    operator fun invoke(id:String) :Flow<MessageUIModel>{
        return cardRepository.deleteCard(id).map { messageMapper.toUIModel(it) }
    }
}
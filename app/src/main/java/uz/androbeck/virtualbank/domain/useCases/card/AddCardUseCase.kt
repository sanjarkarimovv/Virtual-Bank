package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.AddCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import uz.androbeck.virtualbank.domain.ui_models.card.AddCardReqUIModel
import uz.androbeck.virtualbank.domain.ui_models.common.MessageUIModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val addCardMapper: AddCardMapper,
    private val messageMapper: MessageMapper
) {
    suspend operator fun  invoke(uiReqModel: AddCardReqUIModel) : Flow<MessageUIModel> {
        val request = addCardMapper.toDTO(uiReqModel)
        return cardRepository.addCard(request).map { messageMapper.toUIModel(it) }
    }
}

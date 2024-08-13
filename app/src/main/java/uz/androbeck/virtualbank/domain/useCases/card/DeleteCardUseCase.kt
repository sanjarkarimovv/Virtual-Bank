package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.DeleteCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import javax.inject.Inject

class DeleteCardUseCase @Inject constructor(
    private val repository: CardRepository,
    private val deleteCardMapper: DeleteCardMapper
) {
    operator fun deleteCard() = repository.deleteCard().map {
        deleteCardMapper.toUIModel(it)

    }


}
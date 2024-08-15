package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.DeleteCardMapper
import uz.androbeck.virtualbank.domain.mapper.home.MessageMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteCardUseCase @Inject constructor(
    private val cardRepository: CardRepository,
    private val deleteCardMapper: DeleteCardMapper
) {
    suspend operator fun invoke()=cardRepository.deleteCard().map {
        deleteCardMapper.toUIModel(it)
    }
}
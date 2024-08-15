package uz.androbeck.virtualbank.domain.useCases.card

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.card.CardRepository
import uz.androbeck.virtualbank.domain.mapper.card.GetCardsMapper
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val cardsRepository: CardRepository,
    private val cardsMapper: GetCardsMapper
) {
    suspend operator fun invoke() =
        cardsRepository.getCards().map {
            cardsMapper.toUIModel(it)
        }
}
package uz.androbeck.virtualbank.domain.useCases.cards

import kotlinx.coroutines.flow.map
import uz.androbeck.virtualbank.data.repository.cards.GetCardsRepository
import uz.androbeck.virtualbank.domain.mapper.cards.GetCardsMapper
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val cardsRepository: GetCardsRepository,
    private val cardsMapper: GetCardsMapper
) {
    operator fun invoke() =
        cardsRepository.getCards().map {
            cardsMapper.toUIModel(it)
        }
}
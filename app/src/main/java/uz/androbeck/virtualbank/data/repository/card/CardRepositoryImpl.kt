package uz.androbeck.virtualbank.data.repository.card

import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.UpdateCardReqDto
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqDto
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.MessageResDto
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.data.source.remote.card.CardRemoteDataSource
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardRemoteDataSource: CardRemoteDataSource
) : CardRepository {
    override suspend fun deleteCard() = cardRemoteDataSource.deleteCard()
    override suspend fun putUpdateCard(request: UpdateCardReqDto) =
        cardRemoteDataSource.putUpdateCard(request)
) : CardRepository {
    override fun deleteCard() = cardRemoteDataSource.deleteCard()
    override fun addCard(addCardReqDto: AddCardReqDto) =
        cardRemoteDataSource.addCard(addCardReqDto)

}
package uz.androbeck.virtualbank.network.message

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageController @Inject constructor() {

    private val messageHub = Channel<String>()

    fun show(message: String) {
        messageHub.trySend(message)
    }

    fun observeMessage(): Flow<String> {
        return messageHub.consumeAsFlow()
    }
}
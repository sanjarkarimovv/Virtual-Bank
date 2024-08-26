package uz.androbeck.virtualbank.network.errors

import uz.androbeck.virtualbank.network.message.MessageController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandlerImpl @Inject constructor(
    private val messageController: MessageController
) : ErrorHandler {

    override fun handleError(error: Throwable) {
        when (error) {
            is NetworkException -> messageController.show("Internet bilan bog'lanishni tekshiring")
            // buyerda login ekranga navigatsiya qilish ham mumkin
            is UnauthorizedException -> messageController.show("Iltimos qaytadan login qiling")
            is UnknownException -> messageController.show("Nimadir xato, birozdan keyin qayta urunib ko'ring")
            is Error404Exception -> messageController.show(error.message)
            is Error400Exception -> messageController.show(error.message)
            is HttpResponseException -> messageController.show(error.message)
            else -> messageController.show("Kutilmagan xatolik")
        }
    }
}
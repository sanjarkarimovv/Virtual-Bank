package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.domain.useCases.transfer.TransferVerifyUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fullInfoUseCase: GetFullInfoUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {
    fun getUserData(): Flow<ProfileFragmentEvent> = flow {
        fullInfoUseCase().onEach {
            emit(ProfileFragmentEvent.Success(it))
        }.catch {
            errorHandler.handleError(it)
            emit(ProfileFragmentEvent.Error(it.message.toString()))
        }.collect {
            emit(ProfileFragmentEvent.Loading)
        }
    }
}


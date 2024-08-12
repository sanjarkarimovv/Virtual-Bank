package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.full_info

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserFullInfoViewModel @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val fullInfoUseCase: GetFullInfoUseCase
) : BaseViewModel() {
    fun getUserData(): Flow<UserFullInfoEvent> = flow {
        fullInfoUseCase().onEach {
            emit(UserFullInfoEvent.Success(it))
        }.catch {
            errorHandler.handleError(it)
            emit(UserFullInfoEvent.Error(it.message.toString()))
        }.collect {
            emit(UserFullInfoEvent.Loading)
        }
    }
}
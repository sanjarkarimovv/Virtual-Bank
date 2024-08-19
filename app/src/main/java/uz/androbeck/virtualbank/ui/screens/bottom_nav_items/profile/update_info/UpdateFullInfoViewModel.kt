package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqUIModel
import uz.androbeck.virtualbank.domain.useCases.home.PutUpdateInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import javax.inject.Inject

@HiltViewModel
class UpdateFullInfoViewModel @Inject constructor(
    private val errorHandler: ErrorHandler,
    private val useCase: PutUpdateInfoUseCase
) : ViewModel() {
    fun updateUserInfo(updateInfoReqUIModel: UpdateInfoReqUIModel) =
        flow {
            useCase(updateInfoReqUIModel).onEach {
                emit(UpdateFullInfoEvent.Success(it.message.toString()))
            }.catch {
                errorHandler.handleError(it)
                emit(UpdateFullInfoEvent.Error(it.message.toString()))
            }.collect {
                emit(UpdateFullInfoEvent.Loading)
            }
        }
}
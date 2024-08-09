package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile

import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.domain.useCases.home.GetFullInfoUseCase
import uz.androbeck.virtualbank.network.errors.ErrorHandler
import uz.androbeck.virtualbank.ui.base.BaseViewModel
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getFullInfoUseCase: GetFullInfoUseCase,
    private val errorHandler: ErrorHandler
) : BaseViewModel() {
    private val _fullInfoEvent = Channel<FullInfoUIModel>()
    val fullInfoEvent = _fullInfoEvent.consumeAsFlow()

    fun getFullInfo() {
        getFullInfoUseCase().onEach {

            _fullInfoEvent.trySend(it)

        }.catch {
            errorHandler.handleError(it)
            //
        }.launchIn(viewModelScope)
    }

    companion object {
        fun nightMode(dialog: BottomSheetDialog) {
            dialog.run {
                setContentView(R.layout.dialog_widget_setup)
                setCancelable(true)
                setCanceledOnTouchOutside(true)
                show()
            }
            val group = dialog.findViewById<RadioGroup>(R.id.radio_gp)
            group?.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    group.getChildAt(0).id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        dialog.dismiss()
                    }

                    group.getChildAt(1).id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        dialog.dismiss()
                    }

                    group.getChildAt(2).id -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                        dialog.dismiss()
                    }
                }
            }
        }
    }
}
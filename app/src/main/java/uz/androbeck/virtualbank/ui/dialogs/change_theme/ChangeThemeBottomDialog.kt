package uz.androbeck.virtualbank.ui.dialogs.change_theme

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogChangeThemeBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainActivity
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.ui.enums.Theme
import uz.androbeck.virtualbank.utils.extentions.getThemeByCode
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@AndroidEntryPoint
class ChangeThemeBottomDialog : BaseBottomDialog(R.layout.dialog_change_theme) {

    private val binding: DialogChangeThemeBinding by viewBinding()

    @Inject
    lateinit var preferencesProvider: PreferencesProvider
    var onThemeChanged: (() -> Unit)? = null

    private val vm: ChangeThemeViewModel by viewModels()

    //todo
    override fun observe() {
        vm.theme.onEach {
            (requireActivity() as? MainActivity?)?.changeTheme()
            dismiss()
            onThemeChanged?.invoke()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun initialize(view: View) = with(binding) {
        view.background = setCornerRadius()
        when (preferencesProvider.theme.getThemeByCode()) {
            Theme.LIGHT -> {
                changeLightMode.isChecked = true
            }

            Theme.DARK -> {
                changeDarkMode.isChecked = true
            }

            Theme.SYSTEM -> {
                changeThemeSystem.isChecked = true
            }
        }
    }

    override fun clicks() = with(binding) {
        themeLightMode.singleClickable {
            vm.setTheme(Theme.LIGHT.code)
        }
        themeDarkMode.singleClickable {
            vm.setTheme(Theme.DARK.code)
        }
        themeSystem.singleClickable {
            vm.setTheme(Theme.SYSTEM.code)
        }
        changeLightMode.singleClickable {
            vm.setTheme(Theme.LIGHT.code)
        }
        changeDarkMode.singleClickable {
            vm.setTheme(Theme.DARK.code)
        }
        changeThemeSystem.singleClickable {
            vm.setTheme(Theme.SYSTEM.code)
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager) = ChangeThemeBottomDialog().apply {
            show(fragmentManager, this::class.java.simpleName)
        }
    }
}
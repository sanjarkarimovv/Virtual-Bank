package uz.androbeck.virtualbank.ui.dialogs.change_language

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogBottomChangeLanguageBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainActivity
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@AndroidEntryPoint
class ChangeLanguageBottomDialog : BaseBottomDialog(R.layout.dialog_bottom_change_language) {

    private val binding: DialogBottomChangeLanguageBinding by viewBinding()

    @Inject
    lateinit var preferencesProvider: PreferencesProvider
    var onLanguageChanged: (() -> Unit)? = null

    private val vm: ChangeLanguageViewModel by viewModels()

    override fun observe() {
        vm.language.onEach {
            (requireActivity() as? MainActivity?)?.changeLanguage()
            dismiss()
            onLanguageChanged?.invoke()
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun initialize(view: View) = with(binding) {
        view.background = setCornerRadius()
        when (preferencesProvider.language.getLanguageByCode()) {
            Language.UZBEK -> {
                msUzb.isChecked = true
            }

            Language.RUSSIAN -> {
                msRus.isChecked = true
            }

            Language.ENGLISH -> {
                msEng.isChecked = true
            }
        }
    }

    override fun clicks() = with(binding) {
        llUzb.singleClickable {
            vm.setLanguage(Language.UZBEK.code)
        }
        tvUzb.singleClickable {
            vm.setLanguage(Language.UZBEK.code)
        }
        llRus.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
        }
        tvRus.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
        }
        llEng.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
        }
        tvEng.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
        }
    }

    companion object {
        fun show(fragmentManager: FragmentManager) = ChangeLanguageBottomDialog().apply {
            show(fragmentManager, this::class.java.simpleName)
        }
    }
}
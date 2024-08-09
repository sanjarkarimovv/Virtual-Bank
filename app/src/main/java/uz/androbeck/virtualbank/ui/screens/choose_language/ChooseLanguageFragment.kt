package uz.androbeck.virtualbank.ui.screens.choose_language

import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.materialswitch.MaterialSwitch
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentChooseLanguageBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainActivity
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject

@AndroidEntryPoint
class ChooseLanguageFragment : BaseFragment(R.layout.fragment_choose_language) {

    private val binding: FragmentChooseLanguageBinding by viewBinding()
    private val vm: ChooseLanguageViewModel by viewModels()

    @Inject
    lateinit var preferencesProvider: PreferencesProvider

    override fun setup() {
        changeStateLanguageViews(preferencesProvider.language.getLanguageByCode())
    }

    override fun observe() {
        vm.language.onEach {
            (requireActivity() as? MainActivity)?.changeLanguage()
            changeStateLanguageViews(it)
        }.launchIn(
            viewLifecycleOwner.lifecycleScope
        )
    }

    override fun clicks() = with(binding) {
        llEn.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
        }
        tvEn.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
        }
        llRu.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
        }
        tvRu.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
        }
        llUz.singleClickable {
            vm.setLanguage(Language.UZBEK.code)
        }
        tvUz.singleClickable {
            vm.setLanguage(Language.UZBEK.code)
        }
        btnChoose.singleClickable {
            findNavController().navigate(R.id.action_chooseLanguageFragment_to_loginFragment)
        }
    }

    private fun changeStateLanguageViews(language: Language) = with(binding) {
        when (language) {
            Language.UZBEK -> {
                uncheckedLang(llEn, tvEn, switchEn)
                uncheckedLang(llRu, tvRu, switchRu)
                checkedLang(llUz, tvUz, switchUz)
            }

            Language.RUSSIAN -> {
                uncheckedLang(llEn, tvEn, switchEn)
                uncheckedLang(llUz, tvUz, switchUz)
                checkedLang(llRu, tvRu, switchRu)
            }

            Language.ENGLISH -> {
                uncheckedLang(llRu, tvRu, switchRu)
                uncheckedLang(llUz, tvUz, switchUz)
                checkedLang(llEn, tvEn, switchEn)
            }
        }
        tvEn.text = getString(R.string.str_localization_eng)
        tvUz.text = getString(R.string.str_localization_uzb)
        tvRu.text = getString(R.string.str_localization_rus)
        btnChoose.text = getString(R.string.str_choose_title)
        tvTitle.text = getString(R.string.str_choose_language_title)
    }

    private fun checkedLang(ll: LinearLayout, tv: TextView, switch: MaterialSwitch) {
        ll.setBackgroundResource(R.drawable.bg_selectable)
        tv.setTextColor(color(R.color.colorPrimary))
        switch.isChecked = true
    }

    private fun uncheckedLang(ll: LinearLayout, tv: TextView, switch: MaterialSwitch) {
        ll.setBackgroundResource(R.drawable.bg_unselectable)
        tv.setTextColor(color(R.color.colorScrim))
        switch.isChecked = false
    }
}
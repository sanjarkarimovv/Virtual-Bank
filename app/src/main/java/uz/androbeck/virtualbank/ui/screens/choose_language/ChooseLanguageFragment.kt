package uz.androbeck.virtualbank.ui.screens.choose_language

import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.materialswitch.MaterialSwitch
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentChooseLanguageBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.singleClickable

@AndroidEntryPoint
class ChooseLanguageFragment : BaseFragment(R.layout.fragment_choose_language) {

    private val binding: FragmentChooseLanguageBinding by viewBinding()

    override fun setup() {

    }

    override fun clicks() = with(binding) {
        llEn.singleClickable {
            setLanguage(Language.ENGLISH)
        }
        tvEn.singleClickable {
            setLanguage(Language.ENGLISH)
        }
        llRu.singleClickable {
            setLanguage(Language.RUSSIAN)
        }
        tvRu.singleClickable {
            setLanguage(Language.RUSSIAN)
        }
        llUz.singleClickable {
            setLanguage(Language.UZBEK)
        }
        tvUz.singleClickable {
            setLanguage(Language.UZBEK)
        }
        btnChoose.singleClickable{
            findNavController().navigate(R.id.action_chooseLanguageFragment_to_loginFragment)
        }
    }

    private fun setLanguage(language: Language) = with(binding) {
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
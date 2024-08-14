package uz.androbeck.virtualbank.ui.screens.change_language

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentChangeLanguageBinding
import uz.androbeck.virtualbank.preferences.PreferencesProvider
import uz.androbeck.virtualbank.ui.MainActivity
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.ui.enums.Language
import uz.androbeck.virtualbank.utils.extentions.getLanguageByCode
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import javax.inject.Inject
@AndroidEntryPoint
class ChangeLanguageFragment : BaseBottomDialog(R.layout.fragment_change_language) {

    private lateinit var binding: FragmentChangeLanguageBinding

    @Inject
    lateinit var preferencesProvider: PreferencesProvider

    private var listener:OnLanguageChangedListener?=null

    private val vm: ChangeLanguageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentChangeLanguageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setup() {
        super.setup()
        changeStateLanguageViews(preferencesProvider.language.getLanguageByCode())
    }

    override fun observe() {
        super.observe()
        vm.language.onEach {
            (requireActivity() as? MainActivity?)?.changeLanguage()
            changeStateLanguageViews(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnLanguageChangedListener){
            listener=context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.llMain.background = bottomSheetBackground
        setCornerRadius(44f, 44f, 0f, 0f)
    }

    override fun clicks()= with(binding) {
        super.clicks()

        languageUzb.singleClickable {
            vm.setLanguage(Language.UZBEK.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }
        changeLanguageUzb.singleClickable{
            vm.setLanguage(Language.UZBEK.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }
        languageRus.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }
        changeLanguageRus.singleClickable {
            vm.setLanguage(Language.RUSSIAN.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }
        languageEng.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }
        changeLanguageEng.singleClickable {
            vm.setLanguage(Language.ENGLISH.code)
//            (requireActivity() as? MainActivity)?.recreate()
            listener?.onLanguageChanged()
            dismiss()
        }

    }

    private fun changeStateLanguageViews(language: Language) = with(binding) {
        when(language){
            Language.UZBEK -> {
                changeLanguageUzb.isChecked = true
                changeLanguageRus.isChecked = false
                changeLanguageEng.isChecked = false

            }
            Language.RUSSIAN -> {
                changeLanguageUzb.isChecked = false
                changeLanguageRus.isChecked = true
                changeLanguageEng.isChecked = false
            }
            Language.ENGLISH -> {
                changeLanguageUzb.isChecked = false
                changeLanguageRus.isChecked = false
                changeLanguageEng.isChecked = true
            }
        }
        tvTitleUzb.text = getString(R.string.str_localization_uzb)
        tvTitleRus.text = getString(R.string.str_localization_rus)
        tvTitleEng.text = getString(R.string.str_localization_eng)
        tvTitleAppLanguage.text = getString(R.string.application_language)

    }
}
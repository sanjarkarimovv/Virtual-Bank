package uz.androbeck.virtualbank.ui.screens.widgetSettings

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentWidgetSettingsBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class WidgetSettingsFragment : BaseFragment(R.layout.fragment_widget_settings) {
    private val viewModel by viewModels<WidgetSettingsViewModel>()
    private val binding by viewBinding<FragmentWidgetSettingsBinding>()

    private val adapterSelected by lazy {
        WidgetAdapter { item ->
            item?.let {
                it.isShow = false
                viewModel.put(it)
            }
        }
    }
    private val adapterNotSelect by lazy {
        WidgetAdapter { item ->
            item?.let {
                it.isShow = true
                viewModel.put(it)
            }
        }
    }
    override fun setup() {
        setRv()
        viewModel.getUIComponents()
        binding.toolbar.onClickLeftIcon = {
            findNavController().popBackStack()
        }
    }

    private fun setRv() = with(binding) {
        rvSelected.adapter = adapterSelected
        rvNotSelect.adapter = adapterNotSelect
    }

    override fun observe() {
        viewModel.uiEvent.observe(this) {
            when (it) {
                is WidgetSettingsUIEvent.NotShow -> {
                    adapterNotSelect.submitList(emptyList())
                    adapterNotSelect.submitList(it.list)
                    println(it.list.size)
                }

                is WidgetSettingsUIEvent.Show -> {
                    adapterSelected.submitList(emptyList())
                    adapterSelected.submitList(it.list)
                    println(it.list)
                }
            }
        }
    }
}
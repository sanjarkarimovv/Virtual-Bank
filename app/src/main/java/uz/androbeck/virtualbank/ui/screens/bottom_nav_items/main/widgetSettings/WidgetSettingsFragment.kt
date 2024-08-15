package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.widgetSettings

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentWidgetSettingsBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class WidgetSettingsFragment : BaseFragment(R.layout.fragment_widget_settings) {
    private val viewModel by viewModels<WidgetSettingsViewModel>()
    private val binding by viewBinding<FragmentWidgetSettingsBinding>()

    override fun setup() {

    }
}
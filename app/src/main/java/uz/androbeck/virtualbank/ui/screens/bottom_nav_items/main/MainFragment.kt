package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMainBinding
import uz.androbeck.virtualbank.domain.ui_models.home.HomeBodyModels
import uz.androbeck.virtualbank.domain.ui_models.home.UiComponents
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.screens.HomeComponents
import uz.androbeck.virtualbank.utils.extentions.toast

@AndroidEntryPoint
class MainFragment : BaseFragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()
    private val binding: FragmentMainBinding by viewBinding()
    override fun setup() {
        setRv()
    }

    override fun observe() {
        viewModel.homeComponents.observe(this) {
            when (it) {
                // get all components for room data base and show ui custom ui 2
                is HomeComponentsUiEvent.ComponentForUi -> {
                    binding.customBody.isShow(it.list)
                }
            }
        }
    }

    private fun setRv() = with(binding) {
        btnSettings.setOnClickListener {
            toast("Update")
            viewModel.updateComponent(1, true)
        }
    }
}
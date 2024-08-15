package uz.androbeck.virtualbank.ui.dialogs.show_cards

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogShowCardsBinding
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.ui.customViews.forHome.CardsAdapter

@AndroidEntryPoint
class ShowCards : BaseBottomDialog(R.layout.dialog_show_cards) {
    private val binding: DialogShowCardsBinding by viewBinding()
    private val viewModel: ShowCardsViewModel by viewModels()

    private val adapter by lazy {
        ShowDialogAdapter()
    }

    override fun setup() {
        rvSet()
    }

    override fun observe() {
        viewModel.uiEvent.onEach {
            when (it) {
                is ShowDialogUiEvent.AllCards -> {
                    adapter.submitList(it.list)
                }
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun rvSet() {
        binding.rvAllCards.adapter = adapter
    }
}
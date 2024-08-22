package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentHistoryBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment

@AndroidEntryPoint
class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val binding: FragmentHistoryBinding by viewBinding()

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun setup() {
        historyAdapter = HistoryAdapter { child ->
            // RV elematlariga bosganda...
        }
        with(binding) {
            recyclerView.apply {
                adapter = historyAdapter.withLoadStateHeaderAndFooter(
                header = HistoryLoadStateAdapter { historyAdapter.retry() },
                    footer = HistoryLoadStateAdapter { historyAdapter.retry() }
                )
                addItemDecoration(StickyHeaderDecoration(historyAdapter))
            }

            lifecycleScope.launch {
                viewModel.response.collectLatest { pagingData ->
                    historyAdapter.submitData(pagingData)
                }


            }
            //tvIncomeAmount.text = "+ " + viewModel.getAmounts().first.toString()
            //tvOutcomeAmount.text = "- " + viewModel.getAmounts().second.toString()
        }
    }
}





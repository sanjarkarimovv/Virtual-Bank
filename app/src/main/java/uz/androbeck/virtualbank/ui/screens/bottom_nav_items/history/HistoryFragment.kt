package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentHistoryBinding
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

@AndroidEntryPoint
class HistoryFragment : BaseFragment(R.layout.fragment_history) {
    private val binding: FragmentHistoryBinding by viewBinding()

    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter

    override fun setup() {

        setupRecyclerView()
        setupObservers()

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            viewModel.response.collectLatest { pagingData ->
                historyAdapter.submitData(pagingData)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter { child ->
            // RV elematlariga bosganda...
        }
        with(binding) {
            recyclerView.apply {
                adapter = historyAdapter.withLoadStateHeaderAndFooter(
                    header = HistoryLoadStateAdapter { historyAdapter.retry() },
                    footer = HistoryLoadStateAdapter { historyAdapter.retry() }
                )
                historyAdapter.addLoadStateListener {
                    tvIncomeAmount.text = getString(R.string.str_plus) + viewModel.getAmounts().second
                    tvOutcomeAmount.text = getString(R.string.str_minus) + viewModel.getAmounts().first
                    if (it.refresh == androidx.paging.LoadState.Loading) {
                        pbLoading.visible()
                    } else {
                        pbLoading.gone()
                    }
                }
                addItemDecoration(StickyHeaderDecoration(historyAdapter))
            }


        }

    }
}





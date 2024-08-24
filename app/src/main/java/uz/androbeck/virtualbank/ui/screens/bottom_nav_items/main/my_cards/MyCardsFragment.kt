package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMyCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.dialogs.card_options.CardOptionBottomDialog

@AndroidEntryPoint
class MyCardsFragment : BaseFragment(R.layout.fragment_my_cards) {

    private val binding: FragmentMyCardsBinding by viewBinding()
    private val vm: MyCardsViewModel by viewModels()
    private lateinit var pagingAdapter: ViewPagerAdapter
    private lateinit var itemsAdapter: ItemsAdapter

    override fun setup() {



        val listener: (CardUIModel) -> Unit = { card ->
            CardOptionBottomDialog(
                toHistoryScreen = {
                    findNavController().navigate(R.id.action_myCardsFragment_to_cardHistoryFragment)
                },
                toRequisitionScreen = {
                    val bundle = bundleOf("card" to it)
                    findNavController().navigate(
                        R.id.action_myCardsFragment_to_requisitionFragment, bundle
                    )
                },
                toTransferScreen = {
//                    findNavController().navigate(R.id.action_myCardsFragment_to_transferFragment)
                },
                card
            ).show(parentFragmentManager, "")
        }

        pagingAdapter = ViewPagerAdapter(listener)
        binding.swipeRefreshLayout.isRefreshing = false

        binding.viewPager.adapter = pagingAdapter
    }

    override fun observe(): Unit {
        vm.getCardsEvent.onEach {
            when (it) {
                is MyCardsUIEvent.Error -> {
                    binding.swipeRefreshLayout.isRefreshing = false
                }

                is MyCardsUIEvent.Success -> {

                    binding.swipeRefreshLayout.isRefreshing = false
                    val cardSortedList = cardSortedList(it.cards)
                    binding.viewPager.adapter = pagingAdapter

                    pagingAdapter.load(cardSortedList)
                    }

                MyCardsUIEvent.Loading -> {}
                }
            }.launchIn(lifecycleScope)
        }



    override fun clicks()= with(binding){
        swipeRefreshLayout.setOnRefreshListener {
            vm.getCards()
        }

        customToolbar.onClickLeftIcon={
            findNavController().popBackStack()
        }

        btnAddCard.setOnClickListener {
            findNavController().navigate(R.id.action_myCardsFragment_to_addCardFragment)
        }

    }


    private fun cardSortedList(list: List<CardUIModel>): List<List<CardUIModel>> {
        val allList = mutableListOf<CardUIModel>()
        val uzCardList = mutableListOf<CardUIModel>()
        val humoList = mutableListOf<CardUIModel>()
        val visaList = mutableListOf<CardUIModel>()

        list.forEach { cardUIModel ->
            val panPrefix = cardUIModel.pan?.substring(0, 4)
            allList.add(cardUIModel)
            when (panPrefix) {
                "8600", "5614" -> {
                    uzCardList.add(cardUIModel)
                }

                "9860" -> {
                    humoList.add(cardUIModel)
                }
                else -> {}
            }

        }

        val mainList = mutableListOf<List<CardUIModel>>()
        if (allList.isNotEmpty()) {
            mainList.add(allList)
        }
        if (uzCardList.isNotEmpty()) {
            mainList.add(uzCardList)
        }
        if (humoList.isNotEmpty()) {
            mainList.add(humoList)
        }
        if (visaList.isNotEmpty()) {
            mainList.add(visaList)

        }
        return mainList
    }
}





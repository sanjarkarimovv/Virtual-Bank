package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.main.my_cards

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.FragmentMyCardsBinding
import uz.androbeck.virtualbank.domain.ui_models.cards.CardUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.ui.enums.CardType

@AndroidEntryPoint
class MyCardsFragment : BaseFragment(R.layout.fragment_my_cards) {

    private val binding: FragmentMyCardsBinding by viewBinding()
    private val vm: MyCardsViewModel by viewModels()
    private lateinit var pagingAdapter: ViewPagerAdapter
    override fun setup() {

        pagingAdapter = ViewPagerAdapter()

        binding.swipeRefreshLayout.isRefreshing = false

        binding.viewPager.adapter = pagingAdapter

//        pagingAdapter.load(cardSortedList)
//        TabLayoutMediator(
//            binding.tabLayout,
//            binding.viewPager
//        ) { tab, position ->
//            tab.setText(CardType.values()[position].name)
//        }.attach()




    }

    override fun observe(): Unit = with(viewLifecycleOwner.lifecycleScope) {
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
                    TabLayoutMediator(
                        binding.tabLayout,
                        binding.viewPager
                    ) { tab, position ->
                        tab.setText(CardType.values()[position].name)
                    }.attach()

                }
            }
        }.launchIn(this)

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

    private fun loadList(): List<CardUIModel> {
        val getCardsUIModel = listOf<CardUIModel>(
            CardUIModel(
                id = 1,
                amount = 5600.5,
                name = "Personal",
                owner = "Islom Baxtiyorov",
                pan = "5614682005949500",
                expiredMonth = 7,
                expiredYear = 2025,
                themeType = 2,
                isVisible = true
            ),
            CardUIModel(
                id = 1,
                amount = 5600.5,
                name = "Personal",
                owner = "Islom Baxtiyorov",
                pan = "9860682005949500",
                expiredMonth = 7,
                expiredYear = 2025,
                themeType = 3,
                isVisible = false
            ),
            CardUIModel(
                id = 1,
                amount = 5600.5,
                name = "Personal",
                owner = "Islom Baxtiyorov",
                pan = "8600682005949500",
                expiredMonth = 7,
                expiredYear = 2025,
                themeType = 4,
                isVisible = false
            ),
            CardUIModel(
                id = 1,
                amount = 5600.5,
                name = "Personal",
                owner = "Islom Baxtiyorov",
                pan = "8600682005949500",
                expiredMonth = 7,
                expiredYear = 2025,
                themeType = 5,
                isVisible = false

            )
        )
        return getCardsUIModel
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

                else -> {
                    visaList.add(cardUIModel)

                }
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




package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.cards

import android.os.Bundle
import android.view.View
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.optionDialog
import uz.androbeck.virtualbank.utils.extentions.toast

class CardsFragment : BaseFragment(R.layout.fragment_cards) {
    override fun setup() {
    }

    val checklistItems = listOf("variana1", "variant2")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.text_cards).setOnClickListener {
            requireActivity().optionDialog(
                title = "Option Dialog",
                message = "This is option dialog",
                checklistItems = checklistItems,
                icon = null,
                action1 = { si ->
                    val selectedItem = si.map { checklistItems[it] }
                    toast("check: $selectedItem")
                },
                action2 = {
                    toast("message")
                }
            )
        }
    }
}
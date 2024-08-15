package uz.androbeck.virtualbank.ui.screens.update_card

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.card.UpdateCardUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.extentions.toast
import javax.inject.Inject

@AndroidEntryPoint
class UpdateCardFragment : BaseFragment(R.layout.fragment_update_card) {
    private val vm: UpdateCardViewModel by viewModels()

    @Inject
    lateinit var messageController: MessageController
    override fun setup() {
        val reqModel = UpdateCardUIModel(
            id = 3,
            name = "Basic",
            themeType = 3,
            isVisible = false
        )
        vm.updateCard(reqModel)
    }

    override fun observe() {
        messageController.observeMessage().onEach {
            toast(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}
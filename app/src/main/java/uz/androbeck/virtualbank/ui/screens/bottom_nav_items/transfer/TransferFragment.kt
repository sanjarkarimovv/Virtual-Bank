package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.util.Log
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.transfer.GetFeeReqUIModel
import uz.androbeck.virtualbank.network.message.MessageController
import uz.androbeck.virtualbank.ui.base.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class TransferFragment : BaseFragment(R.layout.fragment_transfer) {
    override fun setup() {

    }

}
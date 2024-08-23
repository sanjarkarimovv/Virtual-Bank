package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.update_info

import android.os.Build
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.data.dto.request.home.UpdateInfoReqUIModel
import uz.androbeck.virtualbank.databinding.FragmentUpdateInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.home.FullInfoUIModel
import uz.androbeck.virtualbank.ui.base.BaseFragment
import uz.androbeck.virtualbank.utils.Constants
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.toast
import uz.androbeck.virtualbank.utils.extentions.visible
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class UpdateInfoFragment : BaseFragment(R.layout.fragment_update_info) {
    private val binding by viewBinding(FragmentUpdateInfoBinding::bind)
    private val vm by viewModels<UpdateFullInfoViewModel>()
    private var uiModel: FullInfoUIModel? = null

    override fun setup(): Unit = with(binding) {
        getBundleData()
    }

    override fun clicks() {

    }

    private fun getBundleData() {
        uiModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable(
                Constants.ArgumentKey.USER_FULL_INFO,
                FullInfoUIModel::class.java
            )
        } else {
            arguments?.getSerializable(Constants.ArgumentKey.USER_FULL_INFO) as? FullInfoUIModel
        }
        println(uiModel)
    }


    private fun dateToMillis(dateString: String): Long {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)
        return date?.time ?: 0
    }

    private fun millisToDate(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = Date(milliseconds)
        return dateFormat.format(date)
    }
}
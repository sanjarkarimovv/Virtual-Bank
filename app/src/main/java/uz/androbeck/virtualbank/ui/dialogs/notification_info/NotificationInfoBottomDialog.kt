package uz.androbeck.virtualbank.ui.dialogs.notification_info

import android.annotation.SuppressLint
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.DialogBottomNotificationInfoBinding
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationTransferUIModel
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel
import uz.androbeck.virtualbank.ui.base.BaseBottomDialog
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.visible
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class NotificationInfoBottomDialog(
    private var toNavigation: ((Int)) -> Unit,
    private val notificationTransferUIModel: NotificationTransferUIModel? = null,
    private val notificationUIModel: NotificationUIModel? = null,
) :
    BaseBottomDialog(R.layout.dialog_bottom_notification_info) {
    private val binding by viewBinding(DialogBottomNotificationInfoBinding::bind)

    @SuppressLint("SetTextI18n")
    override fun setup() {
        with(binding) {
            if (notificationTransferUIModel != null) {
                iv.gone()
                ll.visible()
                pan.text = "**** ${notificationTransferUIModel.to?.takeLast(4) ?: ""}"
                tvDescription.text =
                    "${notificationTransferUIModel.from ?: ""} " +
                            getString(R.string.str_from) +
                            "${notificationTransferUIModel.amount}" +
                            getString(R.string.str_soums_transfer_received)
                inTime.text =
                    notificationTransferUIModel.time.formatToDayMonthYearHourMinute()
                btnOpen.text = getString(R.string.str_open_chek)
            } else if (notificationUIModel != null) {
                iv.visible()
                ll.gone()
                notificationUIModel.image?.let { iv.load(it) } ?: iv.gone()
                tvTitle.text = notificationUIModel.title
                tvDescription.text = notificationUIModel.description
                inTime.text = notificationUIModel.time.formatToDayMonthYearHourMinute()
                when (notificationUIModel.navigationType) {
                    1 -> btnOpen.text = getString(R.string.str_update)
                    2 -> btnOpen.text = getString(R.string.str_interest_free_transfer)
                    3 -> btnOpen.text = getString(R.string.paymnet)
                }
            }
        }
    }

    override fun initialize(view: View) {
        view.background = setCornerRadius()
    }

    override fun clicks() = with(binding) {
        btnClose.singleClickable {
            dismiss()
        }
        btnOpen.singleClickable {
            if (notificationUIModel != null) {
                notificationUIModel.navigationType?.let { it1 -> toNavigation(it1) }
            }
        }

    }

    private fun Long.formatToDayMonthYearHourMinute(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy      HH:mm", Locale.getDefault())
        return dateFormat.format(this)
    }
}
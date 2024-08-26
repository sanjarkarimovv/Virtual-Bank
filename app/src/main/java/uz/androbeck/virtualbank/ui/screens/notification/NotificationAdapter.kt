package uz.androbeck.virtualbank.ui.screens.notification

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.ItemNotificationBinding
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationTransferUIModel
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel
import uz.androbeck.virtualbank.domain.ui_models.notification.TimeBasedItem
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.singleClickable
import uz.androbeck.virtualbank.utils.extentions.visible
import java.text.SimpleDateFormat
import java.util.Locale

class NotificationAdapter(
    val context: Context,
    private val onClickTransferMoreBtn: ((NotificationTransferUIModel) -> Unit),
    private val onClickTransferOpen: ((NotificationTransferUIModel) -> Unit),
    private val onClickNotificationMoreBtn: ((NotificationUIModel) -> Unit),
    private val onClickNotificationOpen: ((NotificationUIModel) -> Unit),
) : ListAdapter<TimeBasedItem, RecyclerView.ViewHolder>(diffUtil) {

    companion object {
        private const val VIEW_TYPE_TRANSFER = 1
        private const val VIEW_TYPE_GENERAL = 2

        private val diffUtil = object : DiffUtil.ItemCallback<TimeBasedItem>() {
            override fun areItemsTheSame(oldItem: TimeBasedItem, newItem: TimeBasedItem): Boolean {
                return oldItem.time == newItem.time
            }

            override fun areContentsTheSame(
                oldItem: TimeBasedItem,
                newItem: TimeBasedItem,
            ): Boolean {
                return oldItem.time == newItem.time
            }
        }
    }

    inner class TransferNotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dto: NotificationTransferUIModel) = with(binding) {
            iv.gone()
            inTime.text = dto.time.formatToDayMonthYearHourMinute()
            tvTitle.text = context.getString(R.string.str_you_money_transfer)
            tvDescription.text =
                "${dto.from} " +
                        context.getString(R.string.str_from) +
                        "${dto.amount} " +
                        context.getString(R.string.str_soums_transfer_received)
            btnOpen.text = context.getString(R.string.str_open_chek)
            btnMore.singleClickable { onClickTransferMoreBtn(dto) }
            btnOpen.singleClickable { onClickTransferOpen(dto) }
            container.singleClickable { onClickTransferMoreBtn(dto) }
        }
    }

    inner class GeneralNotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(dto: NotificationUIModel) = with(binding) {
            iv.visible()
            inTime.text = dto.time.formatToDayMonthYearHourMinute()
            tvTitle.text = dto.title
            dto.image?.let { iv.load(it) } ?: iv.gone()
            tvDescription.text = dto.description
            when (dto.navigationType) {
                1 -> btnOpen.text = context.getString(R.string.str_update)
                2 -> btnOpen.text = context.getString(R.string.str_interest_free_transfer)
                3 -> btnOpen.text = context.getString(R.string.paymnet)
            }
            container.singleClickable { onClickNotificationMoreBtn(dto) }
            btnOpen.singleClickable { onClickNotificationOpen(dto) }
            btnMore.singleClickable { onClickNotificationMoreBtn(dto) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NotificationTransferUIModel -> VIEW_TYPE_TRANSFER
            is NotificationUIModel -> VIEW_TYPE_GENERAL
            else -> throw IllegalArgumentException("Unknown ViewType")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_TRANSFER -> {
                val binding = ItemNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                TransferNotificationViewHolder(binding)
            }

            VIEW_TYPE_GENERAL -> {
                val binding = ItemNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                GeneralNotificationViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Unknown ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TransferNotificationViewHolder -> holder.bind(getItem(position) as NotificationTransferUIModel)
            is GeneralNotificationViewHolder -> holder.bind(getItem(position) as NotificationUIModel)
        }
    }

    private fun Long.formatToDayMonthYearHourMinute(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy      HH:mm", Locale.getDefault())
        return dateFormat.format(this)
    }
}


package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androbeck.virtualbank.data.dto.request.card.AddCardReqDto
import uz.androbeck.virtualbank.databinding.ItemUserInfoBinding
import uz.androbeck.virtualbank.utils.extentions.gone
import uz.androbeck.virtualbank.utils.extentions.visible

class TransferUsersAdapter(
    private val onClick: (AddCardReqDto) -> Unit
) : ListAdapter<AddCardReqDto, TransferUsersAdapter.TransferUsersViewHolder>(diffUtil) {
    inner class TransferUsersViewHolder(private val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userInfo: AddCardReqDto) = with(binding) {
            tvUserName.text = userInfo.name
            if (tvUserName.text.isEmpty()) {
            btnAdd.visible()
            rlInfoAboutUser.gone()
            }else{
                btnAdd.gone()
                rlInfoAboutUser.visible()
            }
            root.setOnClickListener {
                onClick(userInfo)
            }
        }
    }

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<AddCardReqDto>() {
            override fun areItemsTheSame(oldItem: AddCardReqDto, newItem: AddCardReqDto): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: AddCardReqDto,
                newItem: AddCardReqDto
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferUsersViewHolder {
        val binding =
            ItemUserInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransferUsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransferUsersViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
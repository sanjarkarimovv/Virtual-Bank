package uz.androbeck.virtualbank.ui.customViews.forHome

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import uz.androbeck.virtualbank.databinding.ItemAdvertisingBinding
import uz.androbeck.virtualbank.domain.ui_models.home.AdvertisingModel
import uz.androbeck.virtualbank.utils.extentions.gone

class AdvertisingAdapter(private val isDataYes: () -> Unit, private val viewPager2: ViewPager2) :
    ListAdapter<AdvertisingModel, AdvertisingAdapter.AdvertisingHolder>(diffUtil) {
    inner class AdvertisingHolder(private val binding: ItemAdvertisingBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(advertisingModel: AdvertisingModel) = with(binding){
                isDataYes.invoke()
                bgImage.load(advertisingModel.uri) {
                    crossfade(true).listener(object : ImageRequest.Listener {
                        override fun onSuccess(request: ImageRequest, result: SuccessResult) {
                            binding.icLoading.gone()
                            super.onSuccess(request, result)
                        }
                    })
                }
            }
    }

    private var isCounter = true
    override fun submitList(list: List<AdvertisingModel>?) {
        super.submitList(list)
        if (list?.isNotEmpty() == true && isCounter) {
            object : CountDownTimer(4000L, 4000L) {
                override fun onTick(p0: Long) {}
                override fun onFinish() {
                    if (currentList.size > viewPager2.currentItem + 1) {
                        viewPager2.currentItem += 1
                    } else {
                        viewPager2.currentItem = 0
                    }
                    start()
                }
            }.start()
            isCounter = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisingHolder {
        return AdvertisingHolder(
            ItemAdvertisingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AdvertisingHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<AdvertisingModel>() {
            override fun areItemsTheSame(
                oldItem: AdvertisingModel,
                newItem: AdvertisingModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AdvertisingModel,
                newItem: AdvertisingModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}

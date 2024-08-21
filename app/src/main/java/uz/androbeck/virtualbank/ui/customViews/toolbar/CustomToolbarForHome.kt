package uz.androbeck.virtualbank.ui.customViews.toolbar

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.animation.addListener
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.databinding.CustomToolbarForHomeBinding

class CustomToolbarForHome @JvmOverloads(
) constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RelativeLayout(context, attrs, defStyle) {
    private val binding =
        CustomToolbarForHomeBinding.inflate(LayoutInflater.from(context), this, true)


    init {
        notificationSettings()
    }

    var clickNotification: (() -> Unit)? = null

    @SuppressLint("Recycle")
    private fun notificationSettings() {
        // in click

            val list = listOf(R.drawable.ic_notification_not, R.drawable.ic_notification_no)
            var index = 0
            with(binding.btnNotification) {
                setOnClickListener {
                    if (!isNotificationYes) {
                    startAnim()
                    clickNotification?.invoke()
                    if (index < list.size)
                        setImageResource(
                            list[index]
                        ) else {
                        index = 0
                        setImageResource(
                            list[index]
                        )
                    }
                    index++

                }
            }
        }
    }

    private fun startAnim() {
        val animator0 =
            ObjectAnimator.ofFloat(binding.btnNotification, "translationX", 0f, 120f)
        val animator1 =
            ObjectAnimator.ofFloat(binding.btnNotification, "translationX", 120f, 0f)
        val scaleY0 =
            ObjectAnimator.ofFloat(binding.btnNotification, "scaleY", 1f, 0.2f)
        val scaleY1 =
            ObjectAnimator.ofFloat(binding.btnNotification, "scaleY", 0.2f, 1f)
        animator0.duration = 200
        animator1.duration = 200
        scaleY0.duration = 200
        scaleY1.duration = 200
        animator0.addListener(onEnd = {
            animator1.start()
            scaleY1.start()
        })

        animator0.start()
        scaleY0.start()
    }

    // in notification yes or no
    private var isNotificationYes: Boolean = false
    fun notificationYes() {
        binding.btnNotification.setImageResource(R.drawable.ic_notification_yes)
        isNotificationYes = true
        startAnim()
    }

    fun notificationNo() {
        isNotificationYes = false
        startAnim()
    }
}
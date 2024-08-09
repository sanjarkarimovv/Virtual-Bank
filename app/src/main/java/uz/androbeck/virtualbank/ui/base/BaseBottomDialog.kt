package uz.androbeck.virtualbank.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.view.WindowMetrics
import android.widget.FrameLayout
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.utils.extentions.color
import uz.androbeck.virtualbank.utils.extentions.dpToPxFloat

abstract class BaseBottomDialog(@LayoutRes private val resId: Int) : BottomSheetDialogFragment() {

    var canFullHeight = false
    private val bgColor by lazy {
        color(R.color.colorSurface)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(resId, container, false)

    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet: FrameLayout =
                    findViewById(com.google.android.material.R.id.design_bottom_sheet)
                val behavior = BottomSheetBehavior.from<View>(bottomSheet)
                bottomSheet.setBackgroundResource(android.R.color.transparent)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.skipCollapsed = true
                onShow(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize(view)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                collect()
            }
        }
        observe()
        setup()
        clicks()
    }

    open fun initialize(view: View) {}
    open suspend fun collect() {}
    open fun observe() {}
    open fun setup() {}
    open fun clicks() {}

    @CallSuper
    open fun onShow(dialogInterface: DialogInterface) {
        if (canFullHeight) {
            val dialog = dialogInterface as BottomSheetDialog
            val sheet: FrameLayout? =
                dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)

            val layoutParams = sheet?.layoutParams

            val windowHeight = getScreenResolution().height

            layoutParams?.height = windowHeight
            sheet?.layoutParams = layoutParams
        }
    }

    val bottomSheetBackground: Drawable by lazy {
        GradientDrawable().apply {
            setColor(bgColor)
            cornerRadius = dpToPxFloat(24f)
        }
    }

    fun setCornerRadius(
        topLeftRadius: Float = 24f,
        topRightRadius: Float = 24f,
        bottomLeftRadius: Float = 0f,
        bottomRightRadius: Float = 0f
    ): Drawable {
        val drawable = GradientDrawable()
        drawable.setColor(bgColor)
        drawable.cornerRadii = floatArrayOf(
            dpToPxFloat(topLeftRadius),
            dpToPxFloat(topRightRadius),
            dpToPxFloat(topLeftRadius),
            dpToPxFloat(topRightRadius),
            dpToPxFloat(bottomLeftRadius),
            dpToPxFloat(bottomRightRadius),
            dpToPxFloat(bottomLeftRadius),
            dpToPxFloat(bottomRightRadius)
        )
        return drawable
    }

    private fun getScreenResolution(): Size {
        with(requireActivity()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                val windowMetrics: WindowMetrics = windowManager.currentWindowMetrics
                val windowInsets = windowMetrics.windowInsets
                val insets =
                    windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars() or WindowInsets.Type.displayCutout())
                val bounds = windowMetrics.bounds
                val width = bounds.width() - insets.left - insets.right
                val height = bounds.height() - insets.top - insets.bottom
                return Size(width, height)
            } else {
                val displayMetrics = DisplayMetrics()
                @Suppress("DEPRECATION")
                windowManager.defaultDisplay.getMetrics(displayMetrics)
                val width = displayMetrics.widthPixels
                val height = displayMetrics.heightPixels
                return Size(width, height)
            }
        }
    }
}
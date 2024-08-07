    package uz.androbeck.virtualbank.ui.screens.pin_code
    
    import android.animation.ValueAnimator
    import androidx.biometric.BiometricManager
    import android.os.Build
    import android.os.Handler
    import android.os.Looper
    import android.view.View
    import android.view.animation.AccelerateDecelerateInterpolator
    import android.view.animation.Interpolator
    import androidx.fragment.app.activityViewModels
    import androidx.fragment.app.viewModels
    import androidx.navigation.fragment.findNavController
    import by.kirich1409.viewbindingdelegate.viewBinding
    import dagger.hilt.android.AndroidEntryPoint
    import uz.androbeck.virtualbank.R
    import uz.androbeck.virtualbank.databinding.FragmentPinCodeBinding
    import uz.androbeck.virtualbank.ui.base.BaseFragment
    import uz.androbeck.virtualbank.ui.events.NavGraphEvent
    import uz.androbeck.virtualbank.ui.screens.MainSharedViewModel
    import uz.androbeck.virtualbank.utils.extentions.gone
    import uz.androbeck.virtualbank.utils.extentions.singleClickable
    import uz.androbeck.virtualbank.utils.extentions.vibrate
    import uz.androbeck.virtualbank.utils.extentions.visible

    @AndroidEntryPoint
    class PinCodeFragment : BaseFragment(R.layout.fragment_pin_code) {
        private val binding by viewBinding(FragmentPinCodeBinding::bind)
        private val pinCodeViewModel: PinCodeViewModel by viewModels()
        private val sharedVM: MainSharedViewModel by activityViewModels()
    
        override fun setup() {
            setupObservers()
            setupClickListeners()
        }
    
        private fun setupClickListeners() {
            with(binding) {
                val buttonIds = listOf(
                    btn01 to getString(R.string.str_num_1),
                    btn02 to getString(R.string.str_num_2),
                    btn03 to getString(R.string.str_num_3),
                    btn04 to getString(R.string.str_num_4),
                    btn05 to getString(R.string.str_num_5),
                    btn06 to getString(R.string.str_num_6),
                    btn07 to getString(R.string.str_num_7),
                    btn08 to getString(R.string.str_num_8),
                    btn09 to getString(R.string.str_num_9),
                    btn00 to getString(R.string.str_num_0)
                )
    
                buttonIds.forEach { (button, digit) ->
                    button.setOnClickListener {
                        vibrate()
                        pinCodeViewModel.addDigit(digit)
                    }
                }
    
                btnRemove.setOnClickListener {
                    vibrate()
                    pinCodeViewModel.removeLastDigit()
                }
    
                listOf(btnConfirm, actionConfirm).forEach { button ->
                    button.setOnClickListener {
                        vibrate()
                        pinCodeViewModel.handlePinCodeCompletion()
                    }
                }
    
                actionExit.setOnClickListener {
                    vibrate()
                    pinCodeViewModel.handlePinCodeExit()
                    navigateWithDelay(NavGraphEvent.Auth, 500)
                }
    
                btnFingerprint.singleClickable {
                    vibrate()
                }
            }
        }
    
        private fun setupObservers() {
            pinCodeViewModel.apply {
                pinCodeList.observe(viewLifecycleOwner) {
                    updatePinCode(it)
                }
    
                fromRegister.observe(viewLifecycleOwner) {
                    with(binding) {
                        if (it == false) {
                            actionExit.gone()
                            actionConfirm.gone()
                            btnConfirm.gone()
                        } else {
                            btnFingerprint.visible()
                            actionExit.visible()
                            actionConfirm.gone()
                            btnConfirm.gone()
                        }
                    }
                }
    
                pinCodeEvent.observe(viewLifecycleOwner) {
                    handlePinCodeEvent(it)
                }
    
                errorAttempts.observe(viewLifecycleOwner) {
                    binding.errorAttempts.text =
                        if (it >= 1) getString(R.string.pin_mismatch_message, 5 - it) else ""
                }
    
                errorLogout.observe(viewLifecycleOwner) {
                    if (it) {
                        navigateWithDelay(NavGraphEvent.Auth, 0)
                    }
                }
            }
        }
    
        private fun handlePinCodeEvent(event: PinCodeEvent) {
            when (event) {
                PinCodeEvent.PinRegistered -> {
    //                navigateWithDelay(NavGraphEvent.Main, 1000)
                    findNavController().navigate(R.id.action_pinCodeFragment_to_biometricPermissionFragment)
                }
    
                PinCodeEvent.PinValidated -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        pinCodeViewModel.clearPinCode()
                    }, 200)
                    Handler(Looper.getMainLooper()).postDelayed({
                        checkPinAnim()
                    }, 400)
                    navigateWithDelay(NavGraphEvent.Main, 1200)
                }
    
                PinCodeEvent.PinValidationFailed -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        pinCodeViewModel.clearPinCode()
                    }, 200)
                    Handler(Looper.getMainLooper()).postDelayed({
                        checkPinAnim()
                    }, 400)
                    Handler(Looper.getMainLooper()).postDelayed({
                        errorPinAnim()
                    }, 1000)
                    Handler(Looper.getMainLooper()).postDelayed({
                        pinCodeViewModel.clearPinCode()
                        binding.errorAttempts.visible()
                    }, 2000)
                }
            }
        }
    
        private fun updatePinCode(pinCode: MutableList<String>) {
            with(binding) {
                val pinViews = listOf(view01, view02, view03, view04)
                pinViews.forEachIndexed { index, view ->
                    view.setBackgroundResource(if (index < pinCode.size) R.drawable.bg_pin_active else R.drawable.bg_pin_inactive)
                }
    
                if (pinCode.size == 4) {
                    if (pinCodeViewModel.fromRegister.value == true) {
                        pinCodeViewModel.handlePinCodeCompletion()
                    } else {
                        actionConfirm.visible()
                        btnConfirm.visible()
                    }
                } else {
                    actionConfirm.gone()
                    btnConfirm.gone()
                }
            }
        }
    
        private fun navigateWithDelay(event: NavGraphEvent, delay: Long) {
            Handler(Looper.getMainLooper()).postDelayed({
                sharedVM.setNavGraphEvent(event)
            }, delay)
        }
    
        private fun checkPinAnim() {
            val pinViews = listOf(binding.view01, binding.view02, binding.view03, binding.view04)
            pinViews.forEachIndexed { index, view ->
                Handler(Looper.getMainLooper()).postDelayed({
                    view.setBackgroundResource(R.drawable.bg_pin_active)
                }, index * 100L)
            }
        }
    
        private fun errorPinAnim(
            view: View = binding.root,
            offset: Float = 100f,
            repeatCount: Int = 4,
            dampingRatio: Float? = null,
            duration: Long = 1000L,
            interpolator: Interpolator = AccelerateDecelerateInterpolator()
        ) {
            val defaultDampingRatio = dampingRatio ?: (1f / (repeatCount + 1))
            val animValues = List((repeatCount * 4) + 1) { i ->
                when (i % 4) {
                    0 -> 0f
                    1 -> -offset * (1 - defaultDampingRatio * (i / 4))
                    2 -> 0f
                    3 -> offset * (1 - defaultDampingRatio * (i / 4))
                    else -> 0f
                }
            }.toFloatArray()
    
            ValueAnimator.ofFloat(*animValues).apply {
                addUpdateListener { view.translationX = it.animatedValue as Float }
                this.interpolator = interpolator
                this.duration = duration
                start()
            }
        }
    
        private fun setButtonsEnabled(enabled: Boolean) {
            with(binding) {
                listOf(
                    btn01, btn02, btn03,
                    btn04, btn05, btn06,
                    btn07, btn08, btn09,
                    btn00, btnRemove, btnFingerprint
                ).forEach {
                    it.isEnabled = enabled
                }
            }
        }
    
    }
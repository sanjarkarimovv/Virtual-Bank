package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.profile.security.models

data class SecuritySettings(
    val useBiometric: Boolean,
    val useBiometricPayment: Boolean,
    val useIsAwayLong: Boolean,
    val awayLongTime: Int
)

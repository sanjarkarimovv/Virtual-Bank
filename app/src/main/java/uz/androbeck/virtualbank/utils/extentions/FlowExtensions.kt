package uz.androbeck.virtualbank.utils.extentions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import uz.androbeck.virtualbank.utils.Constants

fun <T> Flow<T>.share(scope: CoroutineScope) = shareIn(
    scope = scope,
    started = SharingStarted.WhileSubscribed(Constants.Number.FIVE_THOUSAND),
    replay = Constants.Number.ONE
)
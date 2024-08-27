package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.transfer

import android.text.Editable
import android.text.TextWatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebouncingTextWatcher(private val onDebouncedTextChanged: (CharSequence?) -> Unit) :
    TextWatcher {
    private var debounceJob: Job? = null
    private val debouncePeriod: Long = 300 // 300ms debounce period

    override fun afterTextChanged(s: Editable?) {
        debounceJob?.cancel()
        debounceJob = CoroutineScope(Dispatchers.Main).launch {
            delay(debouncePeriod)
            onDebouncedTextChanged(s)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}

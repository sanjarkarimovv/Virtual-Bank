package uz.androbeck.virtualbank.ui.screens.auth.login.logics

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WEText<TYPE : EditText>(
    val list: List<TYPE>
) {
    private val event = MutableLiveData<ETEvent>()

    fun listener(): LiveData<ETEvent> {
        CoroutineScope(Dispatchers.Default).launch {
            var count = 0
            list.forEach { item ->
                var bool = false
                item.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) =
                        if (p0.isNullOrEmpty()) {
                            bool = true
                        } else {
                            bool = false
                        }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                    override fun afterTextChanged(p0: Editable?) {
                        if (p0.isNullOrEmpty()) {
                            event.postValue(
                                ETEvent.Error(
                                    list.indexOf(item),
                                    "this edit text empty ${list.indexOf(item)}"
                                )
                            )
                            count--
                        } else {
                            if (bool) {
                                event.postValue(
                                    ETEvent.SuccessItem(
                                        list.indexOf(item),
                                        "this edit text success ${list.indexOf(item)}"
                                    )
                                )
                                count++
                                if (count == list.size) {
                                    event.postValue(ETEvent.Success("Success all edit text fixed"))
                                }
                            }
                        }
                    }
                })
            }
        }
        return event
    }

    sealed class ETEvent {
        data class Error(
            val index: Int,
            val massage: String
        ) : ETEvent()

        data class SuccessItem(
            val index: Int,
            val massage: String
        ) : ETEvent()

        data class Success(
            val massage: String
        ) : ETEvent()
    }

}

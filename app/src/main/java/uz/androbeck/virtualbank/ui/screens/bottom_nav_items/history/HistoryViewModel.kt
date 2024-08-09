package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.androbeck.virtualbank.data.dto.common.response.transfer.Child
import uz.androbeck.virtualbank.data.dto.common.response.transfer.GetHistoryResDto
import java.util.Calendar

class HistoryViewModel : ViewModel() {

    private val _historyItems = MutableLiveData<List<HistoryItem>>()
    val historyItems: LiveData<List<HistoryItem>> = _historyItems

    init {

        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            val response = listOf(
                GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 759200500
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 759200500
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 859200500
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 759200500
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 259204000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 259290000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 359203000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 359200000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 459200000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 459200000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "outcome",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 459200000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "income",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = 559200000
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                ), GetHistoryResDto(
                    totalElements = "5",
                    totalPages = "1",
                    currentPage = "1",
                    child = listOf(
                        Child(
                            type = "Cash-in",
                            from = "ABC Bank ATM",
                            to = "My Account",
                            amount = 100,
                            time = System.currentTimeMillis()
                        )
                    )
                )
            )

            val items = mutableListOf<HistoryItem>()
            var lastHeaderTime: Long? = null

            response.forEach { response1 ->
                response1.child.forEachIndexed { index, child ->
                    val currentHeaderTime = child.time.toStartOfDay()

                    if (lastHeaderTime == null || lastHeaderTime != currentHeaderTime) {
                        items.add(HistoryItem.Header(currentHeaderTime))
                        lastHeaderTime = currentHeaderTime
                    }

                    items.add(HistoryItem.Content(child))
                }
            }
            _historyItems.value = items
        }
    }

    private fun Long.toStartOfDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = this
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }

    private suspend fun getHistoryFromServer(): GetHistoryResDto {
        // сервердан mалумот келишини  боглаш керак
        return GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                Child(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 100,
                    time = System.currentTimeMillis()
                )
            )
        )
    }
}

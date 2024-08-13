package uz.androbeck.virtualbank.ui.screens.bottom_nav_items.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import uz.androbeck.virtualbank.data.dto.common.response.transfer.Child
import uz.androbeck.virtualbank.data.dto.common.response.transfer.GetHistoryResDto
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import java.util.Calendar

class HistoryViewModelPaging : ViewModel() {

    val historyItems: Flow<PagingData<HistoryItem>> = Pager(PagingConfig(pageSize = 5)) {
        HistoryPagingSource()
    }.flow.cachedIn(viewModelScope)

    private inner class HistoryPagingSource : PagingSource<Int, HistoryItem>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryItem> {
            val page = params.key ?: 1
            return try {
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
                    )
                )

                val items = mutableListOf<HistoryItem>()
                var lastHeaderTime: Long? = null

                response.forEach { responseItem ->

                    responseItem.child.forEach { child ->
                        val currentHeaderTime = child.time.toStartOfDay()

                        if (lastHeaderTime == null || lastHeaderTime != currentHeaderTime) {
                            items.add(HistoryItem.Header(currentHeaderTime))
                            lastHeaderTime = currentHeaderTime
                        }

                        items.add(HistoryItem.Content(child))
                    }
                }

                val allChildren = response.flatMap { it.child }

                LoadResult.Page(
                    data = items,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (allChildren.isEmpty()) null else page + 1
                )
            } catch (exception: Exception) {
                LoadResult.Error(exception)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, HistoryItem>): Int? {
            return state.anchorPosition?.let { position ->
                state.closestPageToPosition(position)?.prevKey?.plus(1)
                    ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
            }
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
}



package uz.androbeck.virtualbank.data.source.remote.history

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.androbeck.virtualbank.data.dto.response.history.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.domain.ui_models.history.HistoryItem
import uz.androbeck.virtualbank.utils.extentions.toStartOfDay

class HistoryPagingSource : PagingSource<Int, HistoryItem>() {

    val response = listOf(
        GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 12346.15F,
                    time = 759200500
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 147.57F,
                    time = 759200500
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 13464.445F,
                    time = 859200500
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1231160.0F,
                    time = 759200500
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 100125.11F,
                    time = 259204000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1000055.0F,
                    time = 259290000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 51616F,
                    time = 359203000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1006551.0F,
                    time = 359200000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1004646.0F,
                    time = 459200000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 6565645.0F,
                    time = 459200000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "outcome",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1.0F,
                    time = 459200000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "income",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 155544.0F,
                    time = 559200000
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 154515.0F,
                    time = System.currentTimeMillis()
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1066464.0F,
                    time = System.currentTimeMillis()
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1654646.0F,
                    time = System.currentTimeMillis()
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 149494800.00F,
                    time = System.currentTimeMillis()
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 1.0F,
                    time = System.currentTimeMillis()
                )
            )
        ), GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 10046.00F,
                    time = System.currentTimeMillis()
                )
            )
        )
    )

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HistoryItem> {
        val page = params.key ?: 1
        return try {

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

    private suspend fun getHistoryFromServer(): GetHistoryResDto {
        // сервердан mалумот келишини  боглаш керак
        return GetHistoryResDto(
            totalElements = "5",
            totalPages = "1",
            currentPage = "1",
            child = listOf(
                InComeAndOutComeResDto(
                    type = "Cash-in",
                    from = "ABC Bank ATM",
                    to = "My Account",
                    amount = 100F,
                    time = System.currentTimeMillis()
                )
            )
        )
    }
}




package uz.androbeck.virtualbank.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import uz.androbeck.virtualbank.data.source.remote.history.HistoryRemoteDataSource
import javax.inject.Inject

class HistoryPagingSource @Inject constructor(
    private val historyRemoteDataSource: HistoryRemoteDataSource
) : PagingSource<Int, GetHistoryResDto>() {
    override fun getRefreshKey(state: PagingState<Int, GetHistoryResDto>): Int? {
        val anchorPosition = state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
        return anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetHistoryResDto> {
        try {
            val nextPageNumber = params.key ?: 0
            val response = historyRemoteDataSource.getHistory(
                size = params.loadSize,
                currentPage = nextPageNumber
            )
              return LoadResult.Page(
                  data = response,
                  prevKey = if(nextPageNumber>1) nextPageNumber-1 else null,
                  nextKey = if(response.isEmpty()) null else nextPageNumber+1,
              )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
package uz.androbeck.virtualbank.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.androbeck.virtualbank.data.api.HistoryService
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
import javax.inject.Inject

class HistoryPagingSource @Inject constructor(
    private val historyService: HistoryService
) : PagingSource<Int, GetHistoryResDto>() {
    override fun getRefreshKey(state: PagingState<Int, GetHistoryResDto>): Int? {
        val archorPosition = state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
        return archorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetHistoryResDto> {
        try {
            val nextPageNumber = params.key ?: 0
            val response = historyService.getHistory(
                size = params.loadSize,
                currentPage = nextPageNumber
            )
          if(response.isSuccessful){
              return LoadResult.Page(
                  data = response.body()?: emptyList(),
                  prevKey = if(nextPageNumber>1) nextPageNumber-1 else null,
                  nextKey = if(response.body().isNullOrEmpty()) null else nextPageNumber+1,
              )
          }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
        return LoadResult.Error(Throwable("Something went wrong"))
    }
}
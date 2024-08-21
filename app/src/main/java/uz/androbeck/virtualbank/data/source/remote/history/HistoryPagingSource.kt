package uz.androbeck.virtualbank.data.source.remote.history

import androidx.paging.PagingSource
import androidx.paging.PagingState
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryPagingSource @Inject constructor(
    private val historyRemoteDataSource: HistoryRemoteDatasource
) : PagingSource<Int, InComeAndOutComeResDto>() {

    override fun getRefreshKey(state: PagingState<Int, InComeAndOutComeResDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InComeAndOutComeResDto> {
        return try {
            val page = params.key ?: 1
            val response =
                historyRemoteDataSource.getHistory(size = params.loadSize, currentPage = page)

            val data = response.transferResDto ?: emptyList()
            println(":::Response Data: $data")

            if (data.isEmpty()) {
                println(":::Warning: Response data is empty")
            }

            LoadResult.Page(
                data = data,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            println(":::Exception $e")
            LoadResult.Error(e)
        }
    }

}

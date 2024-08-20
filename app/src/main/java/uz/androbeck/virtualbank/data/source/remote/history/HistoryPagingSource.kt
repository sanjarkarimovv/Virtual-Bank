package uz.androbeck.virtualbank.data.source.remote.history

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import uz.androbeck.virtualbank.data.dto.common.response.InComeAndOutComeResDto
import uz.androbeck.virtualbank.data.dto.response.history.GetHistoryResDto
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
           println("jdklfsahkfhsjhfkjdshfhkj")
            val responseFlow = historyRemoteDataSource.getHistory(
                size = params.loadSize,
                currentPage = page
            )
            println(":::HistoryPagingSource $responseFlow")
            LoadResult.Page(
                data = responseFlow,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (responseFlow.isEmpty()) null else page + 1
            )

        } catch (e:Exception){
            println(":::Exeption $e")
            LoadResult.Error(e)
        }
    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InComeAndOutComeResDto> {
//        try {
//            val nextPageNumber = params.key ?: 1
//            val response = historyRemoteDataSource.getHistory(
//                size = params.loadSize,
//                currentPage = nextPageNumber
//            )
//            return LoadResult.Page(
//                data = response,
//                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
//                nextKey = if (response.isEmpty()) null else nextPageNumber + 1,
//            )
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }
}
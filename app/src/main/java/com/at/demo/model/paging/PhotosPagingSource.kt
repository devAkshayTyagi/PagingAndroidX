package com.at.demo.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.at.demo.model.bean.PhotoItem
import com.at.demo.model.remote.ApiServices
import com.at.demo.model.repo.PhotoRepository
import retrofit2.HttpException
import java.io.IOException

const val STARTING_PAGE_INDEX = 1

class PhotosPagingSource(
    private val apiServices: ApiServices,
    private val query: String
) : PagingSource<Int, PhotoItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        val apiQuery = query
        return try {
            val response = apiServices.getPhotoList(apiQuery, position, params.loadSize)
            val repos = response.photoData?.photos
            val nextKey = if (repos.isNullOrEmpty()) {
                null
            } else {
                position + (params.loadSize / PhotoRepository.NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos ?: ArrayList(),
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}

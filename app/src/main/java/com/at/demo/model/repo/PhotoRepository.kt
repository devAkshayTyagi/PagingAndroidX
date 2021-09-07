package com.at.demo.model.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.at.demo.model.bean.PhotoItem
import com.at.demo.model.paging.PhotosPagingSource
import com.at.demo.model.remote.ApiServices
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

class PhotoRepository(private val apiServices: ApiServices) :
    BaseRepository(), KoinComponent {

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    /**
     * photos whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getSearchResultStream(query: String): Flow<PagingData<PhotoItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = true,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { PhotosPagingSource(apiServices, query) }

        ).flow
    }


}
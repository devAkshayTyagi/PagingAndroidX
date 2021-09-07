package com.at.demo.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.at.demo.model.bean.PhotoItem
import com.at.demo.model.repo.PhotoRepository
import kotlinx.coroutines.flow.Flow

class PhotoViewModel constructor(
    private val repository: PhotoRepository
) : BaseViewModel() {

    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<PhotoItem>>? = null

    fun searchRepo(queryString: String): Flow<PagingData<PhotoItem>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<PhotoItem>> = repository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }


}
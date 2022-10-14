package com.karthik.instantsearch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.karthik.instantsearch.data.response.Hit
import com.karthik.instantsearch.data.APIService
import com.karthik.instantsearch.data.datasource.ImageSearchPagingSource
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel(private val apiService: APIService) : ViewModel() {

    fun getListData(searchText:String): Flow<PagingData<Hit>> {
        return Pager (config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { ImageSearchPagingSource(apiService,searchText) }).flow.cachedIn(viewModelScope)
    }
}
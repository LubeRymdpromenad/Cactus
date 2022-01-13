package com.example.cactus.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cactus.data.UnsplashData
import com.example.cactus.repository.PER_PAGE
import com.example.cactus.repository.UnsplashDataSource
import com.example.cactus.repository.UnsplashDatastore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class UnsplashViewModel @Inject constructor(
    unsplashDatastore: UnsplashDatastore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    lateinit var unsplashDataSource: UnsplashDataSource

    init {
        val query: String? = savedStateHandle.get<String>("query")
        query?.let {
            unsplashDataSource = UnsplashDataSource(query = it, unsplashDatastore = unsplashDatastore)
        }
    }

    fun getUnsplashData(): Flow<PagingData<UnsplashData>> {
        return Pager(PagingConfig(PER_PAGE)) { unsplashDataSource }.flow
    }
}

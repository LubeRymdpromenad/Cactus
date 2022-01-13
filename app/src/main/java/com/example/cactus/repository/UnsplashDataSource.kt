package com.example.cactus.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cactus.data.UnsplashData


// TODO is this only needed in Config?
const val PER_PAGE = 25

class UnsplashDataSource(
    private val query: String,
    private val unsplashDatastore: UnsplashDatastore
) : PagingSource<Int, UnsplashData>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, UnsplashData> {
        val nextPage = params.key ?: 1

        // TODO add error handling
        val data = unsplashDatastore.searchPhotos(query, nextPage, PER_PAGE)

        return if (data == null) {
            LoadResult.Error(
                Exception("Boom!")
            )
        } else {
            LoadResult.Page(
                data = data,
                prevKey =
                if (nextPage == 1) null
                else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }
    }
}

package com.example.rickandmortywithcompose.data.datasource.remote.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortywithcompose.data.datasource.remote.ApiService
import com.example.rickandmortywithcompose.data.model.CharacterModel
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String?
) :
    PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        try {
            val page = params.key ?: 1
            val response = apiService.getCharacters(page, query)
            val nextKey = if (response.results.isNotEmpty()) page + 1 else null
            return LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}
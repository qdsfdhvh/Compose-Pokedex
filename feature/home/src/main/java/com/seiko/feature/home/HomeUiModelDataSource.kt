package com.seiko.feature.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.seiko.base.mapper.mapToList
import com.seiko.data.repository.PokemonListRepository
import javax.inject.Inject

class HomeUiModelDataSource @Inject constructor(
  private val repository: PokemonListRepository,
  private val mapper: HomeUiModelMapper,
) : PagingSource<Int, HomeUiModel>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeUiModel> {
    return try {
      val page = params.key ?: 0
      val response = repository.fetchPokemonList(page)
      LoadResult.Page(
        data = mapper.mapToList(response.results),
        prevKey = if (page == 0) null else page - 1,
        nextKey = page.plus(1)
      )
    } catch (e: Exception) {
      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, HomeUiModel>): Int? {
    return null
  }

}
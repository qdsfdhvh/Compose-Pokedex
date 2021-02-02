package com.seiko.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.seiko.data.repository.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  pokemonListRepository: PokemonListRepository,
  private val mapper: HomeUiModelMapper,
) : ViewModel() {

  val pokemonList: Flow<PagingData<HomeUiModel>>

  init {
    Timber.d("$this is created")

    pokemonList = pokemonListRepository
      .fetchPokemonList()
      .map { data -> data.map { mapper.mapTo(it) } }
      .cachedIn(viewModelScope)
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }
}
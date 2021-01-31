package com.seiko.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.seiko.base.mapper.mapToList
import com.seiko.data.repository.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  pokemonListRepository: PokemonListRepository,
  private val mapper: HomeUiModelMapper,
) : ViewModel() {

  val pokemonList: LiveData<List<HomeUiModel>>

  init {
    Timber.d("$this is created")

    pokemonList = pokemonListRepository
      .fetchPokemonList(0)
      .map { mapper.mapToList(it) }
      .catch { Timber.w(it) }
      .asLiveData()
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }
}
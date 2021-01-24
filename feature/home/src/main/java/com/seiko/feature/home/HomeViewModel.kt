package com.seiko.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.seiko.data.model.Pokemon
import com.seiko.data.repository.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val pokemonListRepository: PokemonListRepository
) : ViewModel() {

  val pokemonList: LiveData<List<Pokemon>>

  init {
    Timber.d("$this is created")

    pokemonList = pokemonListRepository.fetchPokemonList(0)
      .asLiveData()
  }

  override fun onCleared() {
    super.onCleared()
    Timber.d("$this is clear")
  }
}
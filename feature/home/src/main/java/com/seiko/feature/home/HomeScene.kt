package com.seiko.feature.home

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seiko.base.router.Routes
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.extensions.navViewModel
import com.seiko.common.compose.theme.ComposePokedexTheme
import com.seiko.common.compose.util.ThemedPreview
import com.seiko.common.compose.widget.NetworkImage
import com.seiko.common.compose.widget.StaggeredVerticalGrid
import com.seiko.data.model.Pokemon
import androidx.navigation.compose.navigate

@Composable
fun HomeScene() {
  val vm = navViewModel<HomeViewModel>()
  val pokemonList: List<Pokemon> by vm.pokemonList.observeAsState(emptyList())
  val navController = AmbientNavController.current
  ComposePokedexTheme {
    HomePokemonList(
      pokemonList = pokemonList,
      onClick = { pokemon ->
        navController.navigate(Routes.Detail(pokemon.name))
      }
    )
  }
}

@Composable
fun HomePokemonList(
  pokemonList: List<Pokemon>,
  onClick: (Pokemon) -> Unit,
) {
  ScrollableColumn {
    StaggeredVerticalGrid(
      maxColumnWidth = 220.dp,
      modifier = Modifier.padding(4.dp)
    ) {
      pokemonList.forEach { pokemon ->
        HomePokemon(
          pokemon = pokemon,
          onClick = onClick,
        )
      }
    }
  }
}

@Composable
fun HomePokemon(
  pokemon: Pokemon,
  onClick: (Pokemon) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier
      .padding(4.dp)
      .clickable { onClick(pokemon) },
    color = MaterialTheme.colors.background,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout {
      val (image, title) = createRefs()

      NetworkImage(
        url = pokemon.imageUrl,
        modifier = Modifier
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
          }
          .aspectRatio(0.8f),
        contentScale = ContentScale.Inside
      )

      Text(
        text = pokemon.name,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .constrainAs(title) {
            centerHorizontallyTo(parent)
            top.linkTo(image.bottom)
            bottom.linkTo(parent.bottom)
          }
          .padding(8.dp)
      )
    }
  }
}

@Composable
@Preview
fun HomePokemonPreview() {
  ThemedPreview {
    HomePokemon(
      pokemon = Pokemon(
        page = 1,
        name = "bulbasaur",
        url = "https://pokeapi.co/api/v2/pokemon/1/"
      ),
      onClick = {}
    )
  }
}
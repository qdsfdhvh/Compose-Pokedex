package com.seiko.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.seiko.base.router.Routes
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.extensions.navViewModel
import com.seiko.common.compose.theme.ComposePokedexTheme
import com.seiko.common.compose.util.ThemedPreview
import com.seiko.common.compose.widget.NetworkImage

@Composable
fun HomeScene() {
  val vm = navViewModel<HomeViewModel>()
  val list = vm.pokemonList.collectAsLazyPagingItems()
  val navController = AmbientNavController.current
  ComposePokedexTheme {
    HomePokemonList(
      list = list,
      onClick = { model ->
        navController.navigate(Routes.Detail(model.pokemonName))
      }
    )
  }
}

@Composable
private fun HomePokemonList(
  list: LazyPagingItems<HomeUiModel>,
  onClick: (HomeUiModel) -> Unit,
) {
  @OptIn(ExperimentalFoundationApi::class)
  LazyVerticalGrid(
    cells = GridCells.Fixed(2)
  ) {

    // this state recomposes every time the LazyPagingItems receives an update and changes the
    // recomposerPlaceholder is internal
    @Suppress("UNUSED_VARIABLE")
    val loadState = list.loadState

    val count = list.itemCount
    items(count) { index ->
      val pokemon = list[index] ?: return@items
      HomePokemon(
        model = pokemon,
        onClick = onClick
      )
    }
  }
}

@Composable
fun HomePokemon(
  model: HomeUiModel,
  onClick: (HomeUiModel) -> Unit,
  modifier: Modifier = Modifier,
) {
  Surface(
    modifier = modifier
      .padding(4.dp)
      .clickable { onClick(model) },
    color = MaterialTheme.colors.background,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout {
      val (image, title) = createRefs()

      NetworkImage(
        url = model.pokemonLogo,
        modifier = Modifier
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
          }
          .aspectRatio(0.8f),
        contentScale = ContentScale.Inside
      )

      Text(
        text = model.pokemonName,
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
      model = HomeUiModel(
        pokemonName = "bulbasaur",
        pokemonLogo = "https://pokeres.bastionbot.org/images/pokemon/1.png"
      ),
      onClick = {}
    )
  }
}
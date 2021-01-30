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
import androidx.navigation.compose.navigate
import com.seiko.base.router.Routes
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.extensions.navViewModel
import com.seiko.common.compose.theme.ComposePokedexTheme
import com.seiko.common.compose.util.ThemedPreview
import com.seiko.common.compose.widget.NetworkImage
import com.seiko.common.compose.widget.StaggeredVerticalGrid

@Composable
fun HomeScene() {
  val vm = navViewModel<HomeViewModel>()
  val list by vm.pokemonList.observeAsState(emptyList())
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
fun HomePokemonList(
  list: List<HomeUiModel>,
  onClick: (HomeUiModel) -> Unit,
) {
  ScrollableColumn {
    StaggeredVerticalGrid(
      maxColumnWidth = 220.dp,
      modifier = Modifier.padding(4.dp)
    ) {
      list.forEach { pokemon ->
        HomePokemon(pokemon, onClick)
      }
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
package com.seiko.feature.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.seiko.common.compose.extensions.navViewModel
import com.seiko.common.compose.theme.shimmerHighLight
import com.seiko.common.compose.util.ThemedPreview
import com.seiko.common.compose.widget.AppBarNavigationButton
import com.seiko.common.compose.widget.NetworkImage
import com.skydoves.progressview.ProgressView

typealias JavaColor = android.graphics.Color

@Composable
fun DetailScene(
  pokemonName: String,
) {
  val vm = navViewModel<DetailViewModel>()
  val flow = remember { vm.fetch(pokemonName) }
  val model by flow.observeAsState()
  DetailPokemonInfo(model = model)
}

@Composable
private fun DetailPokemonInfo(
  model: DetailUiModel?,
) {
  Column(
    modifier = Modifier
      .fillMaxHeight()
      .background(Color(JavaColor.parseColor("#2B292B")))
      .verticalScroll(
        state = rememberScrollState(0f),
      ),
  ) {
    ConstraintLayout {
      val (header, arrow) = createRefs()

      Box(
        modifier = Modifier
          .fillMaxWidth()
          .height(260.dp)
          .clip(
            RoundedCornerShape(
              bottomLeftPercent = 20,
              bottomRightPercent = 20
            )
          )
          .background(shimmerHighLight)
          .constrainAs(header) {
            top.linkTo(parent.top)
          },
      )

      AppBarNavigationButton(
        modifier = Modifier
          .constrainAs(arrow) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
          }
      )

      model ?: return@ConstraintLayout

      val (title, logo, weight, height) = createRefs()

      Text(
        text = model.pokemonName,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .constrainAs(title) {
            top.linkTo(header.bottom, 24.dp)
            centerHorizontallyTo(parent)
          }
      )

      NetworkImage(
        url = model.pokemonLogo,
        modifier = Modifier
          .size(190.dp)
          .constrainAs(logo) {
            bottom.linkTo(header.bottom, 20.dp)
            centerHorizontallyTo(parent)
          },
      )

      createHorizontalChain(
        weight,
        height,
        chainStyle = ChainStyle.Spread
      )

      Text(
        text = model.pokemonWeight,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .padding(10.dp)
          .constrainAs(weight) {
            top.linkTo(title.bottom, 24.dp)
          }
      )

      Text(
        text = model.pokemonHeight,
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .padding(10.dp)
          .constrainAs(height) {
            linkTo(weight.top, weight.bottom)
          }
      )

      val (weightTitle, heightTitle) = createRefs()

      Text(
        text = "Weight",
        style = MaterialTheme.typography.body1,
        modifier = Modifier
          .constrainAs(weightTitle) {
            top.linkTo(weight.bottom)
            centerHorizontallyTo(weight)
          }
      )

      Text(
        text = "Height",
        style = MaterialTheme.typography.body1,
        modifier = Modifier
          .constrainAs(heightTitle) {
            top.linkTo(height.bottom)
            centerHorizontallyTo(height)
          }
      )

      val statsTitle = createRef()

      Text(
        text = "Base Stats",
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
          .constrainAs(statsTitle) {
            centerHorizontallyTo(parent)
            top.linkTo(weightTitle.bottom, 12.dp)
          }
      )

      val (statsHp, statsAtk, statsDef, statsSpd, statsExp) = createRefs()

      StatsRow(
        title = "HP",
        progress = model.pokemonHpProgress,
        progressLabel = model.pokemonHpLabel,
        progressColor = JavaColor.parseColor("#D53A47"),
        modifier = Modifier
          .constrainAs(statsHp) {
            top.linkTo(statsTitle.bottom, 12.dp)
          },
      )

      StatsRow(
        title = "ATK",
        progress = model.pokemonAtkProgress,
        progressLabel = model.pokemonAtkLabel,
        progressColor = JavaColor.parseColor("#FFA726"),
        modifier = Modifier
          .constrainAs(statsAtk) {
            top.linkTo(statsHp.bottom, 12.dp)
          }
      )

      StatsRow(
        title = "DEF",
        progress = model.pokemonDefenseProgress,
        progressLabel = model.pokemonDefenseLabel,
        progressColor = JavaColor.parseColor("#0091EA"),
        modifier = Modifier
          .constrainAs(statsDef) {
            top.linkTo(statsAtk.bottom, 12.dp)
          }
      )

      StatsRow(
        title = "SPD",
        progress = model.pokemonSpeedProgress,
        progressLabel = model.pokemonSpeedLabel,
        progressColor = JavaColor.parseColor("#90B1C5"),
        modifier = Modifier
          .constrainAs(statsSpd) {
            top.linkTo(statsDef.bottom, 12.dp)
          }
      )

      StatsRow(
        title = "EXP",
        progress = model.pokemonExpProgress,
        progressLabel = model.pokemonExpLabel,
        progressColor = JavaColor.parseColor("#388E3C"),
        modifier = Modifier
          .constrainAs(statsExp) {
            top.linkTo(statsSpd.bottom, 12.dp)
          }
      )
    }
  }
}

@Composable
fun StatsRow(
  title: String,
  progress: Float,
  progressLabel: String,
  progressColor: Int,
  modifier: Modifier = Modifier,
) {
  Row(
    modifier = modifier
      .padding(start = 32.dp, end = 32.dp)
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.body1,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.width(60.dp)
    )

    AndroidView(
      viewBlock = {
        ProgressView.Builder(it)
          .setRadius(30f.dp.value)
          .setColorBackground(JavaColor.WHITE)
          .setLabelSize(5f)
          .setLabelColorOuter(JavaColor.BLACK)
          .setLabelColorInner(JavaColor.WHITE)
          .setProgressbarColor(progressColor)
          .setMax(1f)
          .setProgress(progress)
          .setLabelText(progressLabel)
          .build()
      },
      modifier = Modifier
        .height(24.dp)
        .fillMaxWidth(),
    )
  }
}

@Preview
@Composable
fun DetailPokemonInfoPreview() {
  ThemedPreview {
    DetailPokemonInfo(
      model = DetailUiModel(
        pokemonName = "bulbasaur",
        pokemonLogo = "https://pokeres.bastionbot.org/images/pokemon/1.png",
        pokemonWeight = "24.0KG",
        pokemonHeight = "11M",
        pokemonHpProgress = 120f / 300,
        pokemonHpLabel = "120/300",
        pokemonAtkProgress = 100f / 300,
        pokemonAtkLabel = "100/300",
        pokemonDefenseProgress = 150f / 300,
        pokemonDefenseLabel = "150/300",
        pokemonSpeedProgress = 80f / 300,
        pokemonSpeedLabel = "80/300",
        pokemonExpProgress = 300f / 1000,
        pokemonExpLabel = "300/1000",
      )
    )
  }
}
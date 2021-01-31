package com.seiko.common.compose.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.seiko.common.compose.AmbientNavController
import com.seiko.common.compose.R

@Composable
fun AppBarNavigationButton(
  modifier: Modifier = Modifier,
  icon: ImageVector = Icons.Default.ArrowBack,
) {
  val navController = AmbientNavController.current
  IconButton(
    onClick = { navController.popBackStack() },
    modifier = modifier
  ) {
    Icon(
      imageVector = icon,
      contentDescription = stringResource(id = R.string.accessibility_common_back)
    )
  }
}
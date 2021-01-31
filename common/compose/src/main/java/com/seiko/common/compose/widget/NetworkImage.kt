package com.seiko.common.compose.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.seiko.common.compose.R
import dev.chrisbanes.accompanist.coil.CoilImage

/**
 * A wrapper around [CoilImage] setting a default [contentScale] and showing
 * an indicator when loading disney poster images.
 *
 * @see CoilImage https://github.com/skydoves/landscapist#coil
 */
@Composable
fun NetworkImage(
  url: String,
  modifier: Modifier,
  contentScale: ContentScale = ContentScale.Crop,
) {
  CoilImage(
    data = url,
    contentDescription = stringResource(id = R.string.accessibility_network_image),
    modifier = modifier,
    contentScale = contentScale,
  )
}
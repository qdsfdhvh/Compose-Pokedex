package com.seiko.feature.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.seiko.common.compose.extensions.navViewModel

@Composable
fun HomeScene() {
  val vm = navViewModel<HomeViewModel>()
  Text(text = "Home")
}
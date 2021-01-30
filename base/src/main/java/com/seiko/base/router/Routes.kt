package com.seiko.base.router

import com.seiko.base.extensions.toUrlEncode

const val initialRoute = Routes.Home

object Routes {

  const val Home = "home"

  object Detail {
    const val argument01 = "pokemonName"
    const val route = "detail/{${argument01}}"
    operator fun invoke(target: String): String {
      return "detail/${target.toUrlEncode()}"
    }
  }

}
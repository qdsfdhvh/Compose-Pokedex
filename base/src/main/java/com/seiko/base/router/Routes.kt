package com.seiko.base.router

import com.seiko.base.extensions.toUrlEncode

object Routes {

  const val Home = "home"

  fun Detail(target: String): String =
    "detail/${target.toUrlEncode()}"

}
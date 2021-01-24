package com.seiko.common.router

import com.seiko.common.extensions.toUrlEncode

object Routes {

  const val Home = "home"

  fun Detail(target: String): String =
    "detail/${target.toUrlEncode()}"

}
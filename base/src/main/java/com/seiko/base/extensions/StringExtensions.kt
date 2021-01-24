package com.seiko.base.extensions

import java.net.URLEncoder

fun String.toUrlEncode(): String = URLEncoder.encode(this, "UTF-8")
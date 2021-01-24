package com.seiko.common.compose

import android.app.Activity
import android.app.Application
import android.view.Window
import androidx.compose.runtime.staticAmbientOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController

val AmbientApplication = staticAmbientOf<Application>()
val AmbientActivity = staticAmbientOf<Activity>()
val AmbientWindow = staticAmbientOf<Window> { error("No window") }
val AmbientNavController = staticAmbientOf<NavController> { error("No NavController") }
val AmbientViewModelProviderFactory = staticAmbientOf<ViewModelProvider.Factory>()

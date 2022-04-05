// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import config.decompose.Pages
import config.decompose.rememberRouter
import config.koin.koinModule
import kotlinx.coroutines.delay
import models.Task
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.inject
import retrofit.TaskController
import service.TaskService

/**
 * https://github.com/JetBrains/compose-jb/tree/master/tutorials
 *
 * router links https://proandroiddev.com/a-comprehensive-hundred-line-navigation-for-jetpack-desktop-compose-5b723c4f256e
 *              https://github.com/JetBrains/compose-jb/blob/master/tutorials/Navigation/README.md
 */

@Composable
@Preview
fun Root() {
    val controller by inject<TaskController>(TaskController::class.java)

    var list by remember { mutableStateOf(listOf<Task>()) }

    LaunchedEffect(Unit) {
        while (true) {
            var cache = listOf<Task>()
            controller.getAllTasks().execute().body()?.let { cache = it }
            list = cache
            delay(5_000)
        }

    }



    val router = rememberRouter<Pages>(
        initialConfiguration = { Pages.List }
    )
    return Children(routerState = router.state) { page ->
        when (val currentPage = page.configuration) {
            is Pages.List ->
                ItemListScreen(
                   list,
                    onItemClick = { router.push(Pages.Details(id = it)) }
                )
            is Pages.Details ->
                ItemDetailScreen(
                    controller.getOneTask(currentPage.id).execute().body() ?: Task(),
                    onClickBack = router::pop
                )
        }.let {}
    }
}


fun main() = application {
    startKoin {
        modules(koinModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "My first App with Decompose Router"
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MaterialTheme {
                Root()
            }
        }
    }
}
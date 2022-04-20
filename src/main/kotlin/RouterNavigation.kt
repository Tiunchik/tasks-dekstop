import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.Children
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import config.decompose.Pages
import config.decompose.rememberRouter
import models.Task
import org.koin.java.KoinJavaComponent
import retrofit.TaskController

@Composable
@Preview
fun Root() {
    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)

    val router = rememberRouter<Pages>(
        initialConfiguration = { Pages.TaskList }
    )
    return Children(routerState = router.state) { page ->
        when (val currentPage = page.configuration) {
            is Pages.TaskList ->
                TaskListScreen(router = router)
            is Pages.TaskDetails ->
                TaskDetailScreen(
                    currentPage.id,
                    router = router
                )
            is Pages.TaskCreate ->
                TaskCreateScreen(router = router)
        }.let {}
    }
}
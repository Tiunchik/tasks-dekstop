import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.pop
import com.arkivanov.decompose.router.push
import config.decompose.Pages
import kotlinx.coroutines.delay
import models.Task
import org.koin.java.KoinJavaComponent
import retrofit.TaskController

@Composable
fun TaskListScreen(router: Router<Pages, Any>) {
    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)
    var list by remember { mutableStateOf(listOf<Task>()) }

    LaunchedEffect(Unit) {
        while (true) {
            var cache = listOf<Task>()
            controller.getAllTasks().execute().body()?.let { cache = it }
            list = cache
            delay(5_000)
        }

    }
    Column {
        TopAppBar(
            title = { Text("Task list screen") },
            navigationIcon = {
                IconButton(onClick = { router.push(Pages.TaskCreate) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
        )
        LazyColumn {
            items(list) {
                Text(
                    text = it.name,
                    modifier = Modifier.clickable { router.push(Pages.TaskDetails(id = it.id)) }
                )
            }
        }
    }

}
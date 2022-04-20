import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.activeChild
import com.arkivanov.decompose.router.pop
import config.decompose.Pages
import models.Task
import org.koin.java.KoinJavaComponent
import retrofit.TaskController

@Composable
fun TaskDetailScreen(itemId: Long, router: Router<Pages, Any>) {
    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)
    val item = controller.getOneTask(itemId).execute().body()

    Column {
        TopAppBar(
            title = { Text("Task details")},
            navigationIcon = {
                IconButton(onClick = router::pop) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        Surface(
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.primary
        ) {
            Column {
                Text(
                    text = item?.name ?: "Information missed"
                )
                Spacer(Modifier.size(4.dp))
                Text(
                    text = item?.description ?: "Information missed"
                )
            }

        }
    }
}
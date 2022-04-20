import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.pop
import config.decompose.Pages
import org.koin.java.KoinJavaComponent
import retrofit.TaskController

@Composable
fun TaskCreateScreen(router: Router<Pages, Any>) {
    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column {
        TopAppBar(
            title = { Text("Create Task") },
            navigationIcon = {
                IconButton(onClick = router::pop) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
        TextField(
            value = name,
            onValueChange = { name = it }
        )
        TextField(
            value = description,
            onValueChange = { description = it }
        )
    }
}
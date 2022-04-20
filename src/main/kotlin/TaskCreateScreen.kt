import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.router.Router
import com.arkivanov.decompose.router.pop
import config.decompose.Pages
import models.Task
import org.koin.java.KoinJavaComponent
import retrofit.TaskController
import retrofit2.awaitResponse

@Composable
fun TaskCreateScreen(router: Router<Pages, Any>) {

    val controller by KoinJavaComponent.inject<TaskController>(TaskController::class.java)
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var blockedButton by remember { mutableStateOf(true) }

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
                IconButton(onClick = {
                    blockedButton = false
                    controller.createTask(Task(name = name, description = description)).execute()
                    name = ""
                    description = ""
                    blockedButton = true
                }, enabled = blockedButton) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send request for creating task"
                    )
                }
            }
        )
        TextField(
            value = name,
            onValueChange = { name = it }
        )

        BasicTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxSize().border(2.dp, color = Color.Black)
        )
    }
}
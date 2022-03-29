import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import models.Item

@Composable
fun ItemDetailScreen(item: Item, onClickBack: () -> Unit) {
    Column {
        TopAppBar(
            title = { Text("Item details")},
            navigationIcon = {
                IconButton(onClick = onClickBack) {
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
            Text(
                text = item.text
            )
        }
    }
}
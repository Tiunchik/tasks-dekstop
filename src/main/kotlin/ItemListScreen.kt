import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import models.Item

@Composable
fun ItemListScreen(itemsList: List<Item>, onItemClick: (id: Long) -> Unit) {
    LazyColumn {
        items(itemsList) {it ->
            Text(
                text = it.text,
                modifier = Modifier.clickable { onItemClick(it.id) }
            )
        }
    }
}
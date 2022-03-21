// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

/**
 * https://github.com/JetBrains/compose-jb/tree/master/tutorials
 */

val suppliedList: Sequence<String> = generateSequence("start") { it + 2 }

fun main() = application {
    Window(
        onCloseRequest = { this.exitApplication() }
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {
    MaterialTheme() {
        ManyElements(suppliedList)
    }
}

@Composable
fun Element(name: String) {
    var newName by remember { mutableStateOf(name) }
    Column {
        Surface(
            color = MaterialTheme.colors.primary,
            elevation = 4.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Greetings(newName)
        }
        TextField(
            value = newName,
            onValueChange = { change -> newName = change }
        )
        Spacer(Modifier.padding(3.dp))
        SubmitPostButton(newName, "Some url for $newName")
    }
}

@Composable
fun ManyElements(names: Sequence<String>) {
    LazyColumn() {
        items(names.take(50).toList()) {
            Element(it)
        }
    }
}

@Composable
fun Greetings(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun SubmitPostButton(data: Any, url: String) {
    Button(
        onClick = {
        println("I send data $data to $url")
    }) {
        Text(text = "Submit")
    }
}
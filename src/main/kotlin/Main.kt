// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
        Element()
    }
}

@Composable
fun Element() {
    var newName by remember { mutableStateOf("none") }
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
            onValueChange = { newName = it })
    }
}

@Composable
fun ManyElements() {
    Column {

    }
}

@Composable
fun Greetings(name: String) {
    Text(text = "Hello $name!")
}
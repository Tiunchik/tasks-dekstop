// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.window.Tray
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.delay

/**
 * https://github.com/JetBrains/compose-jb/tree/master/tutorials
 */

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    var count = remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while (true) {
            count.value++
            delay(1000)
        } 
    }
    MaterialTheme {
        
        Row(Modifier.fillMaxSize(), Arrangement.Center,  Alignment.CenterVertically) {
            Button(onClick = {
                text = "Hello, Desktop!"
            }) {
                Text(text)
            }
            Button(onClick = {
                count.value++
            }) {
                Text("Increase ${count.value}")
            }
        }
    }
}

fun main() = application {
    var isVisible by remember { mutableStateOf(true) }
    Window(
        onCloseRequest = { isVisible = false },
        visible = isVisible,
        title = "My first App"
    ) {
        App()
    }
    if (!isVisible) {
        Tray(
            icon = TryIcon,
            tooltip = "TestApp",
            onAction = { isVisible = true },
            menu = {
                Item("Show", onClick = { isVisible = true})
                Item("Exit", onClick = ::exitApplication)
            }
        )
    }
}

object TryIcon : Painter() {
    override val intrinsicSize: Size = Size(256f, 256f)

    override fun DrawScope.onDraw() {
        drawRect(Color.Red)
    }
}

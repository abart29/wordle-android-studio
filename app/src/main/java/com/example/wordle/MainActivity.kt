package com.example.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.ui.theme.WordleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordleTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WordleApp()
                }
            }
        }
    }
}

@Composable
fun WordleApp() {

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "WORDLE",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(80.dp)
        )
        Row {
            TextBoxRow()
        }

    }
}


@Composable
fun TextBoxRow() {
    val focusManager = LocalFocusManager.current
    var word: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    Row {
        word.forEachIndexed { index, letter: String ->
            TextBox(input = letter, onTextChange = {
                if (it == "" || it[0].isLetter()) {
                    val newList = word.toMutableList()
                    newList[index] = it.uppercase()
                    word = newList
                }
            })
            HandleFocusChange(letter, index, focusManager)
        }
    }
}

@Composable
fun TextBox(input: String, onTextChange: (String) -> Unit) {
    Card(
        modifier = Modifier.size(width = 50.dp, height = 50.dp),
        border = BorderStroke(width = 2.dp, color = Color.Blue)
    ) {
        TextField(
            value = input,
            onValueChange = onTextChange,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),

        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordleTheme {
        WordleApp()
    }
}

@Composable
private fun HandleFocusChange(letter: String, index: Int, focusManager: FocusManager) {
    LaunchedEffect(key1 = letter) {
        if (letter.isNotEmpty() && index != 4) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next
            )
        }
        if (letter.isEmpty() && index != 0) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Previous
            )
        }
    }
}
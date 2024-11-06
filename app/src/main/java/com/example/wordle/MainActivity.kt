package com.example.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.ui.theme.WordleTheme
import java.util.Collections.list

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
fun WordleApp(){

//    var userInput by remember { mutableStateOf(list(5){""})}
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
//            RowOfLetters(
//                answer = "",
//                onValueChange = { },
//                modifier = Modifier
//            )
            TextBoxRow()
        }

    }
}

@Composable
fun RowOfLetters(
    answer: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
//    for (i in 1..5)
//            TextField(
//                value = answer,
//                onValueChange = onValueChange,
//                singleLine = true,
//                modifier = Modifier
//            )
//        TextBox()
}

@Composable
fun TextBoxRow() {
    val focusManager = LocalFocusManager.current
    var letter1 by remember { mutableStateOf("") }
//    val onLetter1Change = { letter1 = it }
    var letter2 by remember { mutableStateOf("") }
    var letter3 by remember { mutableStateOf("") }
    var letter4 by remember { mutableStateOf("") }
    var letter5 by remember { mutableStateOf("") }
    LaunchedEffect(key1 = letter1) {
        if (letter1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next
            )
        }
    }
    LaunchedEffect(key1 = letter2) {
        if (letter1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next
            )
        }
    }
    LaunchedEffect(key1 = letter3) {
        if (letter1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next
            )
        }
    }
    LaunchedEffect(key1 = letter4) {
        if (letter1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next
            )
        }
    }

    Row {
        TextBox(input = letter1, onTextChange = {letter1 = it})
        TextBox(input = letter2, onTextChange = {letter2 = it})
        TextBox(input = letter3, onTextChange = {letter3 = it})
        TextBox(input = letter4, onTextChange = {letter4 = it})
        TextBox(input = letter5, onTextChange = {letter5 = it})
    }
}

@Composable
fun TextBox(input:String, onTextChange: (String) -> Unit) {
//    var input by remember { mutableStateOf("") }
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
            )

        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TextBoxPreview() {
//    WordleTheme {
////        TextBox()
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordleTheme {
        WordleApp()
    }
}
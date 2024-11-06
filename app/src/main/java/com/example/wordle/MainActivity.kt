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
import androidx.compose.runtime.mutableStateListOf
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
import kotlin.reflect.typeOf

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
//    var letter1 by remember { mutableStateOf("") }
//    var letter2 by remember { mutableStateOf("") }
//    var letter3 by remember { mutableStateOf("") }
//    var letter4 by remember { mutableStateOf("") }
//    var letter5 by remember { mutableStateOf("") }

//    val letters = mutableListOf(letter1, letter2, letter3, letter4, letter5)

//    val letters = remember { List(5) { mutableStateOf("") } }

    var word: String by remember { mutableStateOf("") }

//    LaunchedEffect(key1 = word[0]) {
//        if (word[0].isLetter() ) {
//            focusManager.moveFocus(
//                focusDirection = FocusDirection.Next
//            )
//        }
//    }
//    LaunchedEffect(key1 = word[1]) {
//        if (word[1].isLetter()) {
//            focusManager.moveFocus(
//                focusDirection = FocusDirection.Next
//            )
//        }
//    }
//    LaunchedEffect(key1 = word[2]) {
//        if (word[2].isLetter()) {
//            focusManager.moveFocus(
//                focusDirection = FocusDirection.Next
//            )
//        }
//    }
//    LaunchedEffect(key1 = word[3]) {
//        if (word[3].isLetter()) {
//            focusManager.moveFocus(
//                focusDirection = FocusDirection.Next
//            )
//        }
//    }


    Row {
        TextBox(input = word.getOrElse(0) {' '}.toString(), onTextChange = {word += it})
        TextBox(input = word.getOrElse(1) {' '}.toString(), onTextChange = {word += it})
        TextBox(input = word.getOrElse(2) {' '}.toString(), onTextChange = {word += it})
        TextBox(input = word.getOrElse(3) {' '}.toString(), onTextChange = {word += it})
        TextBox(input = word.getOrElse(4) {' '}.toString(), onTextChange = {word += it})
    }
}

@Composable
fun TextBox(input:String, onTextChange: (String) -> Unit) {
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordleTheme {
        WordleApp()
    }
}
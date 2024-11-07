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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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

var wordToGuess = listOf("T", "E", "E", "T", "H")

@Composable
fun WordleApp(){

    var openAlertDialog by remember { mutableStateOf(false) }

    var word1: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word2: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word3: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word4: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word5: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word6: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }


    val shouldShowDialog = remember { mutableStateOf(false) } // 1


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
        if (word1[4].isEmpty()) {
            TextBoxRow(word1) { word1 = it }
        } else {
            WordGuess(word = word1)
            CheckPlayerWins(
                guessedWord = word1,
                onOpenAlert = {shouldShowDialog.value = true}
            )
        }
    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog)
    }
//            if (word1[4].isEmpty()) TextBoxRow(word1) { word1 = it } else WordGuess(word = word1)
//            if (word2[4].isEmpty()) TextBoxRow(word2) { word2 = it } else WordGuess(word = word2)
//            if (word3[4].isEmpty()) TextBoxRow(word3) { word3 = it } else WordGuess(word = word3)
//            if (word4[4].isEmpty()) TextBoxRow(word4) { word4 = it } else WordGuess(word = word4)
//            if (word5[4].isEmpty()) TextBoxRow(word5) { word5 = it } else WordGuess(word = word5)
//            if (word6[4].isEmpty()) TextBoxRow(word6) { word6 = it } else WordGuess(word = word6)
    }
}

@Composable
fun TextBoxRow(word: List<String>, onWordChange: (List<String>) -> Unit) {
    val focusManager = LocalFocusManager.current

    Row {
        word.forEachIndexed { index, letter: String ->
            TextBox(input = letter, index = index, onTextChange = {
                if (it == "" || it[0].isLetter()) {
                    val newList = word.toMutableList()
                    newList[index] = it.uppercase()
                    onWordChange(newList)
                }
            })
            HandleFocusChange(letter, index, focusManager)
        }
    }
}

@Composable
fun TextBox(input: String, index: Int, onTextChange: (String) -> Unit) {

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

@Composable
fun WordGuess(word: List<String>) {
    val borderColors = checkWord(wordToGuess = wordToGuess, guessedWord = word)
    Row {
        word.zip(borderColors).forEach { pair ->

            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                border = BorderStroke(width = 2.dp, color = pair.component2())
            ) {
                Text(pair.component1(), modifier = Modifier.fillMaxSize())
            }
        }
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

@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>) {
    if (shouldShowDialog.value) { // 2
        AlertDialog( // 3
            onDismissRequest = { // 4
                shouldShowDialog.value = false
            },
            // 5
            title = { Text(text = "Alert Dialog") },
            text = { Text(text = "Jetpack Compose Alert Dialog") },
            confirmButton = { // 6
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}


@Composable
private fun CheckPlayerWins (guessedWord: List<String>, onOpenAlert: (Boolean) -> Unit) {
    if (wordToGuess == guessedWord) {
        wordToGuess = listOf("", "", "", "", "")
        onOpenAlert(true)
        }
    }


private fun checkWord(wordToGuess: List<String>, guessedWord: List<String>): MutableList<Color> {
    val borderColorsList = mutableListOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray, Color.Gray)
    val newWordToGuess = wordToGuess.toMutableList()
    val newGuessedWord = guessedWord.toMutableList()

    guessedWord.forEachIndexed { index, letter ->
        if (letter == wordToGuess[index]) {
            borderColorsList[index] = Color.Green
            newWordToGuess[index] = ""
            newGuessedWord[index] = ""
        }
    }
    newGuessedWord.forEachIndexed { index, letter ->
        if (letter.isNotEmpty() && newWordToGuess.contains(letter)) {
            borderColorsList[index] = Color.Yellow
        }
    }

    return borderColorsList
}
package com.example.wordle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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

var wordToGuess = listOf("T", "E", "E", "T", "H")

@Composable
fun InGameScreen(onNextScreen: () -> Unit) {

    var isGameOver by remember { mutableStateOf(false) }

    var word1: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word2: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word3: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word4: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word5: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }
    var word6: List<String> by remember { mutableStateOf(listOf("", "", "", "", "")) }


    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameTitle()
        HandlePlayerGuess(
            word = word1,
            setWord = { word1 = it },
            setIsGameOver = { isGameOver = true })
        HandlePlayerGuess(
            word = word2,
            setWord = { word2 = it },
            setIsGameOver = { isGameOver = true })
        HandlePlayerGuess(
            word = word3,
            setWord = { word3 = it },
            setIsGameOver = { isGameOver = true })
        HandlePlayerGuess(
            word = word4,
            setWord = { word4 = it },
            setIsGameOver = { isGameOver = true })
        HandlePlayerGuess(
            word = word5,
            setWord = { word5 = it },
            setIsGameOver = { isGameOver = true })
        HandlePlayerGuess(
            word = word6,
            setWord = { word6 = it },
            setIsGameOver = { isGameOver = true })

        if (isGameOver) {
            Text("You Win!!!")
            Button(onClick = onNextScreen) {
                Text("Click to finish!")
            }
        }

    }
}

@Composable
fun GameTitle() {
    Text(
        text = "WORDLE",
        fontSize = 45.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(40.dp)
    )
}

@Composable
fun WordInputRow(word: List<String>, onWordChange: (List<String>) -> Unit) {
    val focusManager = LocalFocusManager.current

    Row {
        word.forEachIndexed { index, letter: String ->
            WordInputCard(input = letter, onTextChange = {
                if (it == "" || it[0].isLetter()) {
                    val newList = word.toMutableList()
                    newList[index] = it.uppercase()
                    onWordChange(newList)
                }
            })
            HandleKeyboardFocusChange(letter, index, focusManager)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordInputRowPreview() {
    WordleTheme {
        WordInputRow(listOf("H","E","L","L","O"), onWordChange = {})
    }
}

@Composable
fun WordInputCard(input: String, onTextChange: (String) -> Unit) {

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
fun PlayerGuessResult(word: List<String>) {
    val borderColors = checkWord(wordToGuess = wordToGuess, guessedWord = word)
    Row {
        word.zip(borderColors).forEach { pair ->

            Card(
                modifier = Modifier.size(width = 50.dp, height = 50.dp),
                border = BorderStroke(width = 2.dp, color = pair.component2())
            ) {
                TextField(
                    value = pair.component1(),
                    onValueChange = { },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                    ),
                    readOnly = true
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayerGuessResultPreview() {
    WordleTheme {
        PlayerGuessResult(listOf("H","E","L","L","O"))
    }
}

@Composable
private fun HandleKeyboardFocusChange(letter: String, index: Int, focusManager: FocusManager) {
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
fun HandlePlayerGuess(
    word: List<String>,
    setWord: (List<String>) -> Unit,
    setIsGameOver: (Boolean) -> Unit
) {
    if (word[4].isEmpty()) {
        WordInputRow(word, setWord)
    } else {
        PlayerGuessResult(word = word)
        CheckPlayerWins(
            guessedWord = word,
            setIsGameOver = setIsGameOver
        )
    }
}

@Composable
private fun CheckPlayerWins(
    guessedWord: List<String>,
    setIsGameOver: (Boolean) -> Unit
) {
    if (wordToGuess == guessedWord) {
        setIsGameOver(true)
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

//@Preview(showBackground = true)
//@Composable
//fun InGamePreview() {
//    WordleTheme {
//        InGameScreen(onNextScreen = {})
//    }
//}

package com.example.wordle.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordle.ui.WordleViewModel
import com.example.wordle.ui.theme.WordleTheme


@Composable
fun InGameScreen(
    onNextScreen: () -> Unit,
    wordleViewModel: WordleViewModel = viewModel()
) {
    val uiState by wordleViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GameTitle()
        for (i in 0..5) {
            HandlePlayerGuess(
                wordToGuess = wordleViewModel.wordToGuess,
                word = wordleViewModel.playerGuesses[i].value,
                onGuessChange = { letterIndex: Int, letter: String ->
                    wordleViewModel.updatePlayerGuess(
                        guessIndex = i,
                        letterIndex = letterIndex,
                        letter = letter
                    )
                },
                wordIndex = i,
                currentPlayerWord = uiState.currentPlayerWord,
                playerMakesGuess = { wordleViewModel.playerMakesGuess(it) },
            )
        }

        if (uiState.hasPlayerWon) {
            Text(
                text = "You Win!!!",
                fontSize = 30.sp,
                modifier = Modifier.padding(15.dp)
            )
            Button(onClick = onNextScreen) {
                Text("Click to finish!")
            }
        }
        if (uiState.hasPlayerLost) {
            Text(
                text = "You Lose!!!",
                fontSize = 30.sp,
                modifier = Modifier.padding(15.dp)
            )
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
            .padding(top = 180.dp, bottom = 30.dp)
    )
}

@Composable
fun WordInputRow(word: List<String>, onGuessChange: (Int, String) -> Unit, isDisabled: Boolean) {
    val focusManager = LocalFocusManager.current
    Row {
        word.forEachIndexed { index, letter: String ->
            WordInputCard(input = letter, isDisabled, onGuessChange = {
                onGuessChange(index, it)
            })
            HandleKeyboardFocusChange(letter, index, focusManager)
        }
    }
}


@Composable
fun WordInputCard(input: String, isDisabled: Boolean, onGuessChange: (String) -> Unit) {
    Card(
        modifier = Modifier.size(width = 50.dp, height = 50.dp),
        border = BorderStroke(width = 2.dp, color = Color.Blue)
    ) {
        TextField(
            value = input,
            onValueChange = onGuessChange,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            readOnly = isDisabled
        )
    }
}

@Composable
fun PlayerGuessResult(wordToGuess: List<String>, word: List<String>) {
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
    wordToGuess: List<String>,
    word: List<String>,
    onGuessChange: (Int, String) -> Unit,
    wordIndex: Int,
    currentPlayerWord: Int,
    playerMakesGuess: (Int) -> Unit,
) {
    if (wordIndex == currentPlayerWord) {
        if (word[4].isEmpty()) {
            WordInputRow(word, onGuessChange, false)
        } else {
            PlayerGuessResult(wordToGuess = wordToGuess, word = word)
            playerMakesGuess(wordIndex)
        }
    } else {
        if (word[4].isEmpty()) {
            WordInputRow(word, onGuessChange, true)
        } else {
            PlayerGuessResult(wordToGuess, word = word)
        }
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

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun InGamePreview() {
    WordleTheme {
        InGameScreen(onNextScreen = {})
    }
}


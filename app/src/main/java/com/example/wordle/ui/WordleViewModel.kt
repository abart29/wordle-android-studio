package com.example.wordle.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class WordleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WordleUiState())
    val uiState: StateFlow<WordleUiState> = _uiState.asStateFlow()

    var wordToGuess by mutableStateOf(listOf("T", "E", "E", "T", "H"))
        private set

    val playerGuesses: List<MutableState<List<String>>> = listOf(
        mutableStateOf(listOf("", "", "", "", "")),
        mutableStateOf(listOf("", "", "", "", "")),
        mutableStateOf(listOf("", "", "", "", "")),
        mutableStateOf(listOf("", "", "", "", "")),
        mutableStateOf(listOf("", "", "", "", "")),
        mutableStateOf(listOf("", "", "", "", ""))
    )

    fun playerMakesGuess(
        wordIndex: Int
    ) {
        if (wordIndex == _uiState.value.currentPlayerWord) {
            if (wordToGuess == playerGuesses[wordIndex].value) {
                _uiState.update { currentState ->
                    currentState.copy(hasPlayerWon = true)
                }
            } else if (_uiState.value.currentPlayerWord == 5) {
                _uiState.update { currentState ->
                    currentState.copy(hasPlayerLost = true)
                }
            } else {
                _uiState.update { currentState ->
                    val newPlayerWord = _uiState.value.currentPlayerWord + 1
                    currentState.copy(currentPlayerWord = newPlayerWord)
                }
            }
        }

    }

    fun updatePlayerGuess(
        guessIndex: Int,
        letterIndex: Int,
        letter: String
    ) {
        if (letter == "" || letter[0].isLetter()) {
            val newList = playerGuesses[guessIndex].value.toMutableList()
            newList[letterIndex] = letter.uppercase()
            playerGuesses[guessIndex].value = newList
        }
    }

}
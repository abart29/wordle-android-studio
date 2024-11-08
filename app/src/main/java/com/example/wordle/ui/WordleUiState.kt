package com.example.wordle.ui

data class WordleUiState(
    val hasPlayerWon: Boolean = false,
    val hasPlayerLost: Boolean = false,
    val currentPlayerWord: Int = 0
)

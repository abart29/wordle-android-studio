package com.example.wordle.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.ui.theme.WordleTheme

@Composable
fun StartScreen(onNextScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp).
            fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Text(
        text = "Welcome to Wordle!",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 15.dp)
    )
        WordleRules()
    Button(onClick = onNextScreen) {
        Text("Click to play!")
    }
    }
}

@Composable
fun WordleRules() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "How To Play:",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()

        )
        Text(text = "Objective: Guess the 5-letter hidden word within 6 attempts.")
        Text(text = "Guessing: Enter a valid 5-letter word for each attempt.")
        Text(text = "Feedback:")
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "• Green Border: The letter is correct and in the right position.",
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "• Yellow Border: The letter is in the word but in the wrong position.",
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = "• Gray Border: The letter is not in the word at all.",
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "The game ends after 6 attempts or when the correct word is guessed.")
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    WordleTheme {
        StartScreen(onNextScreen = {})
    }
}
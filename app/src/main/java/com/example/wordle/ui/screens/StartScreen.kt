package com.example.wordle.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordle.ui.theme.WordleTheme

@Composable
fun StartScreen(onNextScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    Text(
        text = "Welcome to Wordle!",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(80.dp)
    )
    Button(onClick = onNextScreen) {
        Text("Click to play!")
    }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    WordleTheme {
        StartScreen(onNextScreen = {})
    }
}
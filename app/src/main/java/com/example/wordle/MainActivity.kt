package com.example.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    var userInput by remember { mutableStateOf(list(5){""})}
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
            RowOfLetters(
                answer = userInput,
                onValueChange = { userInput = it},
                modifier = Modifier
            )
        }
    }
}

@Composable
fun RowOfLetters(
    answer: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    for (i in 1..5)
            TextField(
                value = answer,
                onValueChange = onValueChange,
                singleLine = true,
                modifier = Modifier
            )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WordleTheme {
        WordleApp()
    }
}
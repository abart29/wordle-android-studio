package com.example.wordle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordle.ui.screens.InGameScreen
import com.example.wordle.ui.screens.StartScreen
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

@Composable
fun WordleApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "startScreen") {
        composable("startScreen") {
            StartScreen(onNextScreen = { navController.navigate("inGameScreen")})
        }
        composable("inGameScreen") {
            InGameScreen(onNextScreen = { navController.navigate("startScreen")})
        }
    }


//    composable("question1") {
//        QuestionScreen(
//            questionText = "Where do Manchester United play their home games?",
//            correctAnswer = "Old Trafford",
//            onNextScreen = { navController.navigate("question2") }
//        ) }


//    InGameScreen()
}

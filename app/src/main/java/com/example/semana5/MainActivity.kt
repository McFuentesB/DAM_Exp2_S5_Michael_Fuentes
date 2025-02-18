package com.example.semana5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.semana5.ui.theme.Semana5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Semana5Theme {
                AppScaffold() // Llamamos al Scaffold que contiene el NavGraph
            }
        }
    }
}

@Composable
fun AppScaffold() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Ahora pasamos el modifier al AppNavGraph que envolverá el NavHost
        AppNavGraph(navController = navController)
    }
}


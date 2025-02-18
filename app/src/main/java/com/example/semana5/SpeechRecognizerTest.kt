package com.example.semana5

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class SpeechRecognizerScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var navController: NavHostController

    @Composable
    @Before
    fun setup() {
        navController = rememberNavController()
    }

    @Test
    fun testSpeechRecognizerUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                SpeechRecognizerScreen(navController = navController)
            }

            // Verifica que los elementos de la UI estén presentes
            composeTestRule.onNodeWithText("Reconocedor de Voz").assertIsDisplayed()
            composeTestRule.onNodeWithText("Di algo para comenzar...").assertIsDisplayed()
            composeTestRule.onNodeWithText("Iniciar Reconocimiento").assertIsDisplayed()
            composeTestRule.onNodeWithText("Volver a la pantalla principal").assertIsDisplayed()
        }
    }

    @Test
    fun testStartSpeechRecognitionButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                SpeechRecognizerScreen(navController = navController)
            }

            // Simula el clic en el botón "Iniciar Reconocimiento"
            composeTestRule.onNodeWithText("Iniciar Reconocimiento").performClick()

            // Verifica que se haya iniciado el proceso de reconocimiento de voz
            composeTestRule.onNodeWithText("Di algo para comenzar...").assertIsDisplayed()
        }
    }

    @Test
    fun testSpeechRecognitionResult() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                SpeechRecognizerScreen(navController = navController)
            }

            // Simula que el resultado de la voz es "Hola Mundo"
            val intent = Intent().apply {
                putExtra(RecognizerIntent.EXTRA_RESULTS, arrayListOf("Hola Mundo"))
            }

            // Aquí, deberías invocar manualmente la lógica del launcher para simular el resultado
            // En este caso, simula la recepción del resultado como si fuera exitoso
            composeTestRule.runOnUiThread {
                // Se actualiza el texto de "recognizedText"
                activity.setResult(Activity.RESULT_OK, intent)
            }

            // Verifica que el texto reconocido se muestre en la pantalla
            composeTestRule.onNodeWithText("Hola Mundo").assertIsDisplayed()
        }
    }

    @Test
    fun testBackToMainButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                SpeechRecognizerScreen(navController = navController)
            }

            // Simula el clic en el botón "Volver a la pantalla principal"
            composeTestRule.onNodeWithText("Volver a la pantalla principal").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla principal
            assertTrue(navController.currentBackStackEntry?.destination?.route == "main")
        }
    }
}

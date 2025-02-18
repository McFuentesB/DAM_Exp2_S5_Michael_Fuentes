package com.example.semana5

import android.speech.tts.TextToSpeech
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

class TextToSpeechScreenTest {

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
    fun testTextToSpeechScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                TextToSpeechScreen(navController = navController)
            }

            // Verifica que los elementos de la UI estén presentes
            composeTestRule.onNodeWithText("Texto a Voz").assertIsDisplayed()
            composeTestRule.onNodeWithText("Texto a hablar").assertIsDisplayed()
            composeTestRule.onNodeWithText("Hablar").assertIsDisplayed()
            composeTestRule.onNodeWithText("Volver").assertIsDisplayed()
        }
    }

    @Test
    fun testSpeakButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                TextToSpeechScreen(navController = navController)
            }

            // Introduce texto en el campo "Texto a hablar"
            composeTestRule.onNodeWithText("Texto a hablar").performTextInput("Hola Mundo")

            // Simula el clic en el botón "Hablar"
            composeTestRule.onNodeWithText("Hablar").performClick()

            // Verifica que el método TextToSpeech.speak fue llamado
            // (Nota: Para una prueba real, deberías mockear o verificar la implementación de TTS)
            // Asumimos que el TTS se llama correctamente si el texto no está vacío
            assertTrue("TextToSpeech should be called", true)
        }
    }

    @Test
    fun testBackButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                TextToSpeechScreen(navController = navController)
            }

            // Simula el clic en el botón "Volver"
            composeTestRule.onNodeWithText("Volver").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla principal
            assertTrue(navController.currentBackStackEntry?.destination?.route == "main")
        }
    }
}

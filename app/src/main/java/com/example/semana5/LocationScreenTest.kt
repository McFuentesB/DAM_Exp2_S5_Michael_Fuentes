package com.example.semana5

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.speech.tts.TextToSpeech
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class LocationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java) // Cambia MainActivity por la actividad correcta

    private lateinit var navController: NavController

    @Composable
    @Before
    fun setup() {
        navController = rememberNavController()
    }

    @Test
    fun testLocationScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LocationScreen(
                    context = activity.applicationContext, // Usamos el contexto de la actividad
                    navController = navController
                )
            }

            // Verifica si el texto "Ubicación en tiempo real" está presente
            composeTestRule.onNodeWithText("Ubicación en tiempo real").assertIsDisplayed()

            // Verifica si el botón "Obtener Ubicación" está visible
            composeTestRule.onNodeWithText("Obtener Ubicación").assertIsDisplayed()

            // Verifica si el botón "Leer en voz alta" está visible
            composeTestRule.onNodeWithText("Leer en voz alta").assertIsDisplayed()

            // Verifica si el botón "Volver a la pantalla principal" está visible
            composeTestRule.onNodeWithText("Volver a la pantalla principal").assertIsDisplayed()
        }
    }

    @Test
    fun testFetchLocationButton() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LocationScreen(
                    context = activity.applicationContext, // Usamos el contexto de la actividad
                    navController = navController
                )
            }

            // Simula el clic en el botón "Obtener Ubicación"
            composeTestRule.onNodeWithText("Obtener Ubicación").performClick()

            // Verifica que se muestra un mensaje de error si no se obtiene la ubicación
            composeTestRule.onNodeWithText("No se pudo obtener la ubicación").assertIsDisplayed()
        }
    }

    @Test
    fun testTextToSpeechButton() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LocationScreen(
                    context = activity.applicationContext, // Usamos el contexto de la actividad
                    navController = navController
                )
            }

            // Simula el clic en el botón "Leer en voz alta"
            composeTestRule.onNodeWithText("Leer en voz alta").performClick()

            // Verifica que el texto del botón haya cambiado a "Ubicación no disponible" (o el texto por defecto)
            composeTestRule.onNodeWithText("Ubicación no disponible").assertIsDisplayed()
        }
    }

    @Test
    fun testNavigateBackToMainScreen() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LocationScreen(
                    context = activity.applicationContext, // Usamos el contexto de la actividad
                    navController = navController
                )
            }

            // Simula el clic en el botón "Volver a la pantalla principal"
            composeTestRule.onNodeWithText("Volver a la pantalla principal").performClick()

            // Verifica que la navegación ha cambiado correctamente (puedes usar un navController.mock de la navegación)
            // Aquí, por ejemplo, verificamos si la navegación hacia la pantalla principal ha sucedido
            assertTrue(navController.currentBackStackEntry?.destination?.route == "main")
        }
    }
}

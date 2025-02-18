package com.example.semana5

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

class MainScreenTest {

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
    fun testMainScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                MainScreen(navController = navController)
            }

            // Verifica que los elementos del UI estén presentes
            composeTestRule.onNodeWithText("Pantalla Principal").assertIsDisplayed()
            composeTestRule.onNodeWithText("Texto a Voz").assertIsDisplayed()
            composeTestRule.onNodeWithText("Voz a Texto").assertIsDisplayed()
            composeTestRule.onNodeWithText("Ubicacion en tiempo real").assertIsDisplayed()
            composeTestRule.onNodeWithText("Cerrar sesión").assertIsDisplayed()
        }
    }

    @Test
    fun testTextToSpeechButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                MainScreen(navController = navController)
            }

            // Simula el clic en el botón "Texto a Voz"
            composeTestRule.onNodeWithText("Texto a Voz").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de Texto a Voz
            assertTrue(navController.currentBackStackEntry?.destination?.route == "text_to_speech")
        }
    }

    @Test
    fun testSpeechRecognizerButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                MainScreen(navController = navController)
            }

            // Simula el clic en el botón "Voz a Texto"
            composeTestRule.onNodeWithText("Voz a Texto").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de Voz a Texto
            assertTrue(navController.currentBackStackEntry?.destination?.route == "speech_recognizer")
        }
    }

    @Test
    fun testLocationScreenButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                MainScreen(navController = navController)
            }

            // Simula el clic en el botón "Ubicacion en tiempo real"
            composeTestRule.onNodeWithText("Ubicacion en tiempo real").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de ubicación
            assertTrue(navController.currentBackStackEntry?.destination?.route == "location_screen")
        }
    }

    @Test
    fun testLogoutButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                MainScreen(navController = navController)
            }

            // Simula el clic en el botón "Cerrar sesión"
            composeTestRule.onNodeWithText("Cerrar sesión").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de login
            assertTrue(navController.currentBackStackEntry?.destination?.route == "login")
        }
    }
}

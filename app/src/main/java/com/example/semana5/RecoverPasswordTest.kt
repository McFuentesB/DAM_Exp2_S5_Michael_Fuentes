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

class RecoverPasswordScreenTest {

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
    fun testRecoverPasswordScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RecoverPasswordScreen(navController = navController)
            }

            // Verifica que los elementos de la UI estén presentes
            composeTestRule.onNodeWithText("Recuperar Contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Usuario").assertIsDisplayed()
            composeTestRule.onNodeWithText("Nueva Contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Recuperar Contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Volver al login").assertIsDisplayed()
        }
    }

    @Test
    fun testRecoverPasswordButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RecoverPasswordScreen(navController = navController)
            }

            // Introduce valores en los campos
            composeTestRule.onNodeWithText("Usuario").performTextInput("usuario_test")
            composeTestRule.onNodeWithText("Nueva Contraseña").performTextInput("nueva_password")

            // Simula el clic en el botón "Recuperar Contraseña"
            composeTestRule.onNodeWithText("Recuperar Contraseña").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de login
            assertTrue(navController.currentBackStackEntry?.destination?.route == "login")
        }
    }

    @Test
    fun testBackToLoginButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RecoverPasswordScreen(navController = navController)
            }

            // Simula el clic en el botón "Volver al login"
            composeTestRule.onNodeWithText("Volver al login").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de login
            assertTrue(navController.currentBackStackEntry?.destination?.route == "login")
        }
    }
}

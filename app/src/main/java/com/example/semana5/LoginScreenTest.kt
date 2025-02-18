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

class LoginScreenTest {

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
    fun testLoginScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LoginScreen(navController = navController)
            }

            // Verifica que los elementos del UI estén presentes
            composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
            composeTestRule.onNodeWithText("Usuario").assertIsDisplayed()
            composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Iniciar sesión").assertIsDisplayed()
            composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate").assertIsDisplayed()
            composeTestRule.onNodeWithText("Recuperar contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Sobre Nosotros").assertIsDisplayed()
        }
    }

    @Test
    fun testLoginButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LoginScreen(navController = navController)
            }

            // Simula el clic en el botón "Iniciar sesión" cuando los campos no están vacíos
            composeTestRule.onNodeWithText("Usuario").performTextInput("user123")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password")
            composeTestRule.onNodeWithText("Iniciar sesión").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla principal
            assertTrue(navController.currentBackStackEntry?.destination?.route == "main")
        }
    }

    @Test
    fun testRegisterButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LoginScreen(navController = navController)
            }

            // Simula el clic en el botón "¿No tienes cuenta? Regístrate"
            composeTestRule.onNodeWithText("¿No tienes cuenta? Regístrate").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de registro
            assertTrue(navController.currentBackStackEntry?.destination?.route == "register")
        }
    }

    @Test
    fun testRecoverPasswordButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                LoginScreen(navController = navController)
            }

            // Simula el clic en el botón "Recuperar contraseña"
            composeTestRule.onNodeWithText("Recuperar contraseña").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de recuperación de contraseña
            assertTrue(navController.currentBackStackEntry?.destination?.route == "recover_password")
        }
    }
}

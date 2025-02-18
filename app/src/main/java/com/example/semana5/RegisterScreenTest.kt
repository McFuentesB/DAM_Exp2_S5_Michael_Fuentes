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

class RegisterScreenTest {

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
    fun testRegisterScreenUI() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RegisterScreen(navController = navController)
            }

            // Verifica que los elementos de la UI estén presentes
            composeTestRule.onNodeWithText("Crear Cuenta").assertIsDisplayed()
            composeTestRule.onNodeWithText("Usuario").assertIsDisplayed()
            composeTestRule.onNodeWithText("Contraseña").assertIsDisplayed()
            composeTestRule.onNodeWithText("Registrar Usuario").assertIsDisplayed()
            composeTestRule.onNodeWithText("¿Ya tienes una cuenta? Inicia sesión").assertIsDisplayed()
        }
    }

    @Test
    fun testRegisterButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RegisterScreen(navController = navController)
            }

            // Introduce valores en los campos
            composeTestRule.onNodeWithText("Usuario").performTextInput("usuario_test")
            composeTestRule.onNodeWithText("Contraseña").performTextInput("password_test")

            // Simula el clic en el botón "Registrar Usuario"
            composeTestRule.onNodeWithText("Registrar Usuario").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de login
            assertTrue(navController.currentBackStackEntry?.destination?.route == "login")
            // Verifica que el usuario se haya agregado correctamente
            assertTrue(users.contains(Pair("usuario_test", "password_test")))
        }
    }

    @Test
    fun testBackToLoginButtonClicked() {
        activityScenarioRule.scenario.onActivity { activity ->
            composeTestRule.setContent {
                RegisterScreen(navController = navController)
            }

            // Simula el clic en el botón "¿Ya tienes una cuenta? Inicia sesión"
            composeTestRule.onNodeWithText("¿Ya tienes una cuenta? Inicia sesión").performClick()

            // Verifica que la navegación haya ocurrido hacia la pantalla de login
            assertTrue(navController.currentBackStackEntry?.destination?.route == "login")
        }
    }
}

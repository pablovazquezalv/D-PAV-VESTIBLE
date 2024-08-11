/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.dpav.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.dpav.R
import com.dpav.presentation.models.ContextProvider
import com.dpav.presentation.models.KtorClient
import com.dpav.presentation.models.UserPreferences
import com.dpav.presentation.models.valToken
import com.dpav.presentation.models.validarToken
import com.dpav.presentation.screens.LoginFirstScreen
import com.dpav.presentation.screens.LoginSecondScreen
import com.dpav.presentation.screens.MenuScreen
import com.dpav.presentation.screens.PrincipalScreen
import com.dpav.presentation.theme.DPAVTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        ContextProvider.init(applicationContext)
        val userPreferences = UserPreferences(applicationContext)

        setContent {
            val isLoggIn = userPreferences.isLoggedIn

            LaunchedEffect(Unit) {
                //val token = userPreferences.getToken()
                withContext(Dispatchers.IO) {
                    if (!valToken())
                        userPreferences.logout()
                }

            }

            val navController = rememberSwipeDismissableNavController()
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = if (isLoggIn) "MenuScreen" else "FirstLoginScreen"
            ){
                composable("FirstLoginScreen") {
                    LoginFirstScreen(navController)
                }
                composable("SecondLoginScreen") {
                    LoginSecondScreen(navController)
                }
                composable("PrincipalScreen") {
                    PrincipalScreen(navController)
                }
                composable("MenuScreen") {
                    MenuScreen(navController)
                }
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "MenuScreen"
    ){
        composable("FirstLoginScreen") {
            LoginFirstScreen(navController)
        }
        composable("SecondLoginScreen") {
            LoginSecondScreen(navController)
        }
        composable("PrincipalScreen") {
            PrincipalScreen(navController)
        }
        composable("MenuScreen") {
            MenuScreen(navController)
        }
    }
}
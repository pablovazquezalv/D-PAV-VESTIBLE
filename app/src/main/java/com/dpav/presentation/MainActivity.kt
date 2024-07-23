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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.dpav.R
import com.dpav.presentation.screens.LoginFirstScreen
import com.dpav.presentation.screens.LoginSecondScreen
import com.dpav.presentation.theme.DPAVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberSwipeDismissableNavController()
            SwipeDismissableNavHost(
                navController = navController,
                startDestination = "FirstLoginScreen"
            ){
                composable("FirstLoginScreen") {
                    LoginFirstScreen(navController)
                }
                composable("SecondLoginScreen") {
                    LoginSecondScreen()
                }
            }
        }
    }
}

@Composable
fun MyBackground(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MyImg(R.drawable.dpav, "dpav", Modifier.size(100.dp, 50.dp))
            MyTextField()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ){
                MyImg(R.drawable.imgfondo, "fondo", Modifier)
            }
        }
    }
}

@Composable
fun MyTextField(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.background,
        text = "Para iniciar sesion dale al boton para generar un codigounico de sesion."
    )
}

@Composable
fun MyImg(imgId: Int, desc: String, modifier: Modifier){
    Image(
        modifier = modifier,
        painter = painterResource(id = imgId),
        contentDescription = desc
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "FirstLoginScreen"
    ){
        composable("FirstLoginScreen") {
            LoginFirstScreen(navController)
        }
        composable("SecondLoginScreen") {
            LoginSecondScreen()
        }
    }
}
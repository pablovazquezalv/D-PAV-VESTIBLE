package com.dpav.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.wear.protolayout.ModifiersBuilders.Border
import com.dpav.presentation.models.ContextProvider
import com.dpav.presentation.models.UserPreferences


@Composable
fun MenuScreen(navController: NavController){
    MenuBody(navController)
}

@Composable
fun MenuBody(navController: NavController){
    val userPreferences = UserPreferences(ContextProvider.context)
    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(17.dp,20.dp,10.dp,10.dp)
        ){
            Text(text = "Menu", color = Color.Black, modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp))
            MenuItem("Ver Mis Mascotas") { navController.navigate(route = "PrincipalScreen") }
            MenuItem("Cerrar Sesion") {
                userPreferences.logout()
                navController.navigate(route = "FirstLoginScreen"){
                    // Evita que se creen múltiples instancias de la pantalla
                    launchSingleTop = true

                    // Opcional: Elimina todas las pantallas anteriores de la pila
                    popUpTo("FirstLoginScreen") {
                        inclusive = true
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(22.dp,0.dp,22.dp,20.dp)
            .size(width = 150.dp, height = 35.dp)
    ) {
        Text(text = text, color = MaterialTheme.colors.onBackground)
    }
}
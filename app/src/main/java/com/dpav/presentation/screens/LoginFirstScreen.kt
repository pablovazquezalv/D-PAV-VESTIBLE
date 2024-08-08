package com.dpav.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.dpav.R

val ButtonBlue = Color(0xFF1A73E8)

@Composable
fun LoginFirstScreen(navController: NavController){
    MyBackground(navController)
}


@Composable
fun MyBackground(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MyImg(R.drawable.dpav, "dpav", Modifier.size(100.dp, 50.dp))
            NavButton(text = "Iniciar Sesion", navController = navController)
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
fun MyTextField(text: String){
    Text(
        modifier = Modifier.size(150.dp, 30.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.background,
        text = text
    )
}

@Composable
fun NavButton(text: String, navController: NavController){
    Button(
        onClick = {
            navController.navigate(route = "SecondLoginScreen")
            //println("Response: Hola mUndo")
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(22.dp).size(width = 150.dp, height = 35.dp)
    ) {
        Text(text = text, color = MaterialTheme.colors.onBackground)
    }
}

@Composable
fun MyImg(imgId: Int, desc: String, modifier: Modifier){
    Image(
        modifier = modifier,
        painter = painterResource(id = imgId),
        contentDescription = desc
    )
}


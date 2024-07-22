package com.dpav.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.dpav.R


@Composable
fun LoginFirstScreen(navController: NavController){
    MyBackground()
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
            MyTextField("Para iniciar sesion dale al boton para generar un codigounico de sesion.")
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
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.background,
        text = text
    )
}

@Composable
fun MyButton(text: String, color: Colors, modifier: Modifier){
    Button(onClick = { /*TODO*/ }) {
        
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


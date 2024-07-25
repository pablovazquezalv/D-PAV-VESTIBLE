package com.dpav.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.dpav.R
import com.dpav.presentation.models.Login
import kotlinx.coroutines.launch

@Composable
fun LoginSecondScreen(/*navController: NavController*/){
    LoginSecondScreenBody()
}

@Composable
fun LoginSecondScreenBody(){
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            MyImg(R.drawable.dpav, "dpav", Modifier.size(100.dp, 50.dp))
            MyTextField("Ingrese codigo para iniciar sesion")
            TextField(value = textState, onValueChange = { newText ->
                textState = newText
                if (newText.text.length == 4) { // Asumiendo que el PIN es de 4 dígitos
                    coroutineScope.launch {
                        try {
                            val dataToSend = Login(codigo = newText.text)
                            val response = postApiData("https://api.example.com/data", dataToSend)
                            println("Response: ${response.readText()}")
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }, modifier = Modifier.padding(16.dp).size(width = 150.dp, height = 20.dp))
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ){
                MyImg(R.drawable.imgfondo, "fondo", Modifier)
            }
        }
    }
}



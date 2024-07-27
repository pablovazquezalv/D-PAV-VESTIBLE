package com.dpav.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.dpav.R
import com.dpav.presentation.models.Login
import com.dpav.presentation.models.getLogin
import com.google.gson.Gson
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import kotlinx.coroutines.launch

@Composable
fun LoginSecondScreen(navController: NavController){
    LoginSecondScreenBody()
}

@Composable
fun LoginSecondScreenBody(){
    var text by remember { mutableStateOf("") }
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
            TextField(
                value = text,
                onValueChange = { text = it },
                keyboardOptions = KeyboardOptions(keyboardType  = KeyboardType.Number),
                textStyle = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(0.dp,16.dp,0.dp,0.dp)
                    .size(width = 150.dp, height = 50.dp)
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ){
                Button(
                    onClick = {
                        if (text.length == 6) { // Asumiendo que el PIN es de 6 dígitos
                            coroutineScope.launch {
                                val gson = Gson()
                                try {
                                    val code = Login(codigo = text)
                                    val codeSerialized = gson.toJson(code)
                                    val response = getLogin(codeSerialized)
                                    println("Response: ${response.bodyAsText()}")
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = ButtonBlue),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.padding(0.dp,15.dp,0.dp,0.dp).size(width = 140.dp, height = 35.dp)
                ) {
                    Text(text = "Enviar", color = MaterialTheme.colors.onBackground)
                }
            }
        }
    }
}



package com.dpav.presentation.screens

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.dpav.R
import com.dpav.presentation.models.Perro
import com.dpav.presentation.models.UserPreferences
import com.dpav.presentation.models.fetchPerros
import com.dpav.presentation.models.getPerros
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun PrincipalScreen(navController: NavController){
    BodyPrincipalScreen(navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyPrincipalScreen(navController: NavController){
    var perros by remember { mutableStateOf<List<Perro>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    LaunchedEffect(Unit) {
        //val token = userPreferences.getToken()
        withContext(Dispatchers.IO) {
            try {
                val fetchedPerros = fetchPerros()
                if (fetchedPerros != null) {
                    perros = fetchedPerros
                }
            } catch (e: Exception) {
                // Manejo de errores
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground),
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.background,
                text = "Mis Mascotas",
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold
            )
            ListaDePerros(perros = perros)
        }
    }
}

@Composable
fun ListaDePerros(perros: List<Perro>) {
    LazyColumn {
        items(perros) { perro ->
            PerroItem(perro = perro)
        }
    }
}

@Composable
fun PerroItem(perro: Perro){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(35.dp, 7.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 4.dp,
        backgroundColor = Color(0xFF4285F4),
        //onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = perro.imagen.replace("\\", ""),
                contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = perro.nombre, color = Color.White, fontSize = 15.sp)
                Text(text = perro.raza, color = Color.LightGray, fontSize = 9.sp)
            }
        }
    }
}

@Composable
fun HighlightedText(text: String, highlightColor: Color, isBold: Boolean) {
    Text(
        text = text,
        color = highlightColor,
        fontSize = 20.sp,
        fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
    )
}
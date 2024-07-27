package com.dpav.presentation.screens

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
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
import com.dpav.R

@Composable
fun PrincipalScreen(navController: NavController){
    BodyPrincipalScreen()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyPrincipalScreen(){
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
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(35.dp,10.dp),
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
                    Image(
                        painter = painterResource(id = R.drawable.dpav),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "Tequila", color = Color.White, fontSize = 15.sp)
                        Text(text = "Pomeriana", color = Color.LightGray, fontSize = 9.sp)
                    }
                }
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
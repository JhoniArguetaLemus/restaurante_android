package com.adev.restaurante.view

import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.adev.restaurante.ui.theme.Restaurante1

@OptIn(ExperimentalMaterial3Api::class)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun LoginView(navController: NavController){

    var contrasenna by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFE1C56E), Color(0xFF000000)) // Puedes usar cualquier combinación de colores
    )


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {Text("Restaurante Argueta", fontSize = 25.sp)},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Restaurante1
                )

            )
        }
        
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Restaurante1
                ),
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(0.90f)

            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp)
                ) {
                    Text("Bienvenido", fontSize = 50.sp)

                    Spacer(Modifier.height(50.dp))
                    OutlinedTextField(
                        value = usuario,
                        onValueChange = {usuario=it},
                        placeholder ={ Text("usuario")},


                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White
                        ),
                        maxLines = 1


                    )

                    Spacer(Modifier.height(50.dp))

                    OutlinedTextField(
                        value = contrasenna,
                        onValueChange = {contrasenna=it},
                        placeholder ={ Text("contraseña")},


                        shape = RoundedCornerShape(15.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        maxLines = 1


                    )

                    Spacer(Modifier.height(30.dp))
                    OutlinedButton(
                        onClick = {navController.navigate("MenuView")},
                        modifier = Modifier

                            .fillMaxWidth(0.6f)
                            .background(gradient, shape = RoundedCornerShape(50))
                        ,
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        ),
                        border = BorderStroke(1.dp, Color.Transparent)

                    ) {
                        Text("Iniciar sesión", color = Color.White)
                    }


                }



            }




        }
        
        
    }
}
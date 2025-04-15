package com.adev.restaurante.components

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.adev.restaurante.R
import com.adev.restaurante.model.CartItem
import com.adev.restaurante.model.Product
import com.adev.restaurante.ui.theme.Restaurante1
import com.adev.restaurante.viewmodel.CartViewModel

@Composable

fun ProductCard(product: Product, viewModel: CartViewModel){

    var cantidad by remember { mutableIntStateOf(1) }

    var showDialog by remember { mutableStateOf(false) }

    if(showDialog){
        AlertDialog(
            title = { Text("Notificación", fontSize = 25.sp)},
            text = { Text("El producto fue agregado correctamente", fontSize = 20.sp)},
            onDismissRequest = {showDialog=false},
            confirmButton = {
                Button(onClick = {showDialog=false},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Restaurante1
                    )
                    ) {
                    Text("Ok")
                }
            }
        )
    }

    val grandient=Brush.linearGradient(
        colorStops = arrayOf(
            0.0f to Restaurante1.copy(alpha = 0.9f),  // Desde el inicio hasta el 50%
            0.5f to Restaurante1.copy(alpha = 0.8f),
            0.5f to Restaurante1.copy(alpha = 0.8f),
            1.0f to Color.White.copy(alpha = 0.8f)
        )
    )

    OutlinedCard (
        modifier = Modifier
            .height(350.dp)
            .width(230.dp)
        ,
        elevation = CardDefaults.cardElevation(20.dp)
    ){
        Image(
            painter = painterResource(product.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // bordes superiores redondeados
        )
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(product.productName, fontSize = 15.sp)

            Spacer(Modifier.height(20.dp))

            Text("$ ${product.price}", fontSize = 20.sp, color =Color.Black, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (cantidad>1){
                        cantidad--
                    }

                },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Restaurante1
                    )

                ) {
                    Icon(painter = painterResource(R.drawable.minus), contentDescription = null)
                }



                TextField(
                    value = cantidad.toString(),
                    readOnly = true,
                    onValueChange = {
                        val num = it.toIntOrNull()
                        if (num != null && num >= 0) {
                            cantidad = num
                        }
                    },
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .weight(0.5f)
                        .height(50.dp),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Restaurante1,
                        focusedContainerColor = Restaurante1
                    )
                )
                IconButton(onClick = {
                    cantidad++
                },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Restaurante1
                    ),

                ) {
                    Icon(Icons.Default.Add, contentDescription = null,

                        )
                }
            }

            Spacer(Modifier.height(20.dp))
           
          Button(onClick = {
              viewModel.addItemToCart(CartItem(product.id, product.productName, product.price, cantidad, product.price*cantidad))
              showDialog=true
          },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
              modifier = Modifier.background(grandient, shape = RoundedCornerShape(50)),
              shape = RoundedCornerShape(50)

          ) {
                Text("Agregar al carrito")
            }

        }
    }

}
package com.adev.restaurante.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adev.restaurante.R
import com.adev.restaurante.model.CartItem

@Composable
fun CartItemView(item:CartItem){

    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(200.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.LightGray) // Puedes cambiar este color
    ) {
        Image(
            painter = painterResource(R.drawable.hamburguesa),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(200.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
        )

       Column(
           modifier = Modifier.padding(10.dp)
               .weight(1f),

           verticalArrangement = Arrangement.spacedBy(10.dp)

       ) {
           Text(item.name, fontSize = 20.sp)
           Text("$ ${item.price}", fontSize = 20.sp)
           Text("x${item.quantity}", fontSize = 20.sp)
           Text("Total: $ ${item.total}", fontSize = 20.sp)
       }
    }


}
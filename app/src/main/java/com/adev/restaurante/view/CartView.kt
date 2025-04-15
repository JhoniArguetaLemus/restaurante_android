package com.adev.restaurante.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.adev.restaurante.components.CartItemView
import com.adev.restaurante.ui.theme.Restaurante1
import com.adev.restaurante.viewmodel.CartViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun CartView(viewModel: CartViewModel, navController: NavController) {
    val cartItems by viewModel.cartItems.collectAsState()

   Scaffold(
       topBar = {
           CenterAlignedTopAppBar(
               title = { Text("Carrito")},
               colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                   containerColor = Restaurante1
               ),
               navigationIcon = {
                   IconButton(onClick = {navController.popBackStack()}) {
                       Icon(Icons.Default.ArrowBack,
                           contentDescription = "Back",
                           modifier = Modifier.size(60.dp)
                       )
                   }
               }
           )
       }
   ){  paddingValues ->

       Column(modifier = Modifier
           .padding(paddingValues),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           LazyColumn(modifier = Modifier
               .fillMaxWidth()
               .padding(top = 50.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.spacedBy(10.dp)
           ) {


               items(cartItems){item->

                   CartItemView(item)
               }



           }
           

       }




   }

}

package com.adev.restaurante.view

import android.annotation.SuppressLint
import android.widget.ActionMenuView
import androidx.activity.compose.ReportDrawnWhen
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.createBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.adev.restaurante.R
import com.adev.restaurante.components.NavItem
import com.adev.restaurante.components.ProductCard
import com.adev.restaurante.model.Product
import com.adev.restaurante.ui.theme.Restaurante1
import com.adev.restaurante.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuView(cartViewModel: CartViewModel, navController: NavController){

    val items by cartViewModel.cartItems.collectAsState()

    var selectedItem by remember { mutableIntStateOf(0) }

    val navList = listOf(
        NavItem(
            "Home",
            R.drawable.comida_sana
        ),
        NavItem(
            "Vegetariana",
           R.drawable.comida_sana
        ),
        NavItem(
            "Bebidas",
            R.drawable.sodas
        ),
        NavItem(
            "Postres",
            R.drawable.pastel
        )
    )

    val products= listOf(

        //categories
        //1:fast food, 2: vegetables/healty meal, 3:sodas, 4: desserts
        Product(
            1, "Galletas", 3.50,1, R.drawable.hamburguesa
        ),

        Product(2, "Carne a la plancha", 3.50, 1, R.drawable.hamburguesa),

        Product(3, "Hamburguesa de cebolla", 3.50, 1, R.drawable.hamburguesa),

        //category2

        Product(4, "Galletas", 3.50,2, R.drawable.ensalada_cesar),


        Product(2, "Carne a la plancha", 3.30, 2, R.drawable.ensalada_cesar),

        Product(3, "Hamburguesa de cebolla", 3.30, 2, R.drawable.ensalada_cesar),

        //category 3

        Product(4, "Galletas", 3.40,3, R.drawable.coca),
        Product(4, "Galletas", 3.40,3, R.drawable.coca),
        Product(4, "Galletas", 3.40,3, R.drawable.coca),

        //category 4

        Product(4, "Galletas", 3.70,4, R.drawable.postre),
        Product(4, "Galletas", 3.70,4, R.drawable.postre),
        Product(4, "Galletas", 3.70,4, R.drawable.postre),

    )


    var filteredProducts by remember(selectedItem) {
        mutableStateOf(
            when (selectedItem) {
                0 -> products.filter { it.category == 1 }
                1 -> products.filter { it.category == 2 }
                2 -> products.filter { it.category ==3 }
                3 -> products.filter { it.category ==4 }
                else -> emptyList()
            }
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Restaurante Argueta",
                        fontSize = 25.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Restaurante1
                ),
                actions = {



                      IconButton(
                            onClick = {navController.navigate("CartView")},
                          modifier = Modifier

                        ) {

                            BadgedBox(
                                badge = {
                                    if(items.isNotEmpty()){
                                        Badge(
                                            containerColor =Color.Red   ,
                                            contentColor = Color.White,
                                            modifier = Modifier.padding(5.dp)

                                        ){
                                            Text("${items.size}", fontSize = 12.sp)
                                        }

                                    }
                                }
                            ) {
                                Icon(
                                    Icons.Default.ShoppingCart,
                                    contentDescription = "cart",
                                    modifier = Modifier.size(40.dp)

                                )

                            }
                        }



                }
            )
        },
        bottomBar = {
            NavigationBar {

                navList.forEachIndexed{index, navItem ->
                    NavigationBarItem(
                        selected = selectedItem==index,
                        onClick = {selectedItem=index},
                        label = { Text(navItem.label)},
                        enabled = true,
                        colors =NavigationBarItemDefaults.colors(
                            selectedIconColor = Restaurante1,
                            selectedTextColor = Restaurante1
                        ),
                        icon = {Icon(painter = painterResource(navItem.icon),
                            contentDescription = "home",
                            modifier = Modifier.size(35.dp)
                        )}
                    )
                }

            }
        }

    ) {  paddingValues ->


        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {


            LaunchedEffect(selectedItem) {
                filteredProducts= when (selectedItem) {
                    0 -> products.filter { it.category == 1 }
                    1 -> products.filter { it.category == 2 }
                    2 -> products.filter { it.category == 3 }
                    3 -> products.filter { it.category == 4 }
                    else -> emptyList() // Handle unexpected cases
                }
            }

            Menu(products = filteredProducts, cartViewModel = cartViewModel)

        }








    }


}


@Composable
fun Menu(products:List<Product>,cartViewModel: CartViewModel){


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(16.dp)
        ) {

            items(products){product->
                ProductCard(product, cartViewModel)

            }

        }

}
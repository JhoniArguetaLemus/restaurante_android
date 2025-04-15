package com.adev.restaurante.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adev.restaurante.model.CartItem
import com.adev.restaurante.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CartViewModel :ViewModel() {
    private val _cartItems= MutableStateFlow<List<CartItem>>(emptyList())

    val cartItems:StateFlow<List<CartItem>> = _cartItems.asStateFlow()


    fun addItemToCart(product: CartItem) {
        _cartItems.update { currentItems ->
            if (currentItems.any { it.productId == product.productId }) {
                currentItems.map {
                    if (it.productId == product.productId) {
                        it.copy(quantity = it.quantity + product.quantity)
                    } else {
                        it
                    }
                }
            } else {
                currentItems + product
            }
        }
    }




    fun updateItemQuantity(productId:Int, newQuantity:Int){
        _cartItems.update { currentItems->
            currentItems.map {
                if(it.productId==productId){
                    it.copy(quantity = newQuantity)
                }else{
                    it
                }
            }

        }
    }

    fun cleartCart(){
        _cartItems.update { emptyList() }
    }

    fun getTotalPrice():Double{
        return _cartItems.value.sumOf { it.price*it.quantity }
    }

}
package com.adev.restaurante.model

data class CartItem (
    val productId:Int,
    val name:String,
    val price:Double,
    val quantity:Int=1,
    val total:Double
)
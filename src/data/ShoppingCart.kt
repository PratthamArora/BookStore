package com.pratthamarora.data

data class ShoppingCart(
    var id: String,
    var userId: String,
    val items: ArrayList<ShoppingItems>
)
package com.pratthamarora.data.model

data class ShoppingCart(
    var id: String,
    var userId: String,
    val items: ArrayList<ShoppingItems>
)
package com.pratthamarora.data.model

import io.ktor.locations.Location

@Location("/book/list")
data class BookListLocation(
    val sortBy: String,
    val asc: Boolean
)
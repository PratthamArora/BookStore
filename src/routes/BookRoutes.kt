package com.pratthamarora.routes

import com.pratthamarora.data.DataManagerMongoDB
import com.pratthamarora.data.model.BookListLocation
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.books() {

    val dataManager = DataManagerMongoDB()

    authenticate("bookStoreAuth") {
        get<BookListLocation>() {
            call.respond(dataManager.sortedBooks(it.sortBy, it.asc))
        }
    }

}

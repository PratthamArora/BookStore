package com.pratthamarora.routes

import com.pratthamarora.data.DataManager
import com.pratthamarora.data.model.Book
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.books() {
    val dataManager = DataManager()
    route("/book") {
        get("/") {
            call.respond(dataManager.allBooks())
        }

        post("/{id}") {
            val id = call.parameters["id"]
            val receiveBook = call.receive(Book::class)
            val updateBook = dataManager.updateBook(receiveBook)
            call.respond { updateBook }
        }

        put("") {
            val receiveBook = call.receive(Book::class)
            val newBook = dataManager.addNewBook(receiveBook)
            call.respond { newBook }
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            val deletedBook = dataManager.deleteBook(id)
            call.respond { deletedBook }
        }
    }
}
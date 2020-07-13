package com.pratthamarora.routes

import com.pratthamarora.data.DataManager
import com.pratthamarora.data.model.Book
import com.pratthamarora.data.model.BookListLocation
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.locations.get
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.books() {

    val dataManager = DataManager()

    authenticate("bookStoreAuth") {
        get<BookListLocation>() {
            call.respond(dataManager.sortedBooks(it.sortBy, it.asc))
        }
    }

    route("/book") {
        get("/") {
            call.respond(dataManager.allBooks())
        }

        post("/{id}") {
            val id = call.parameters["id"]
            val receiveBook = call.receive(Book::class)
            val updateBook = dataManager.updateBook(receiveBook)
            updateBook?.let { it1 -> call.respond(it1) }
        }

        put("") {
            val receiveBook = call.receive(Book::class)
            val newBook = dataManager.addNewBook(receiveBook)
            call.respond(newBook)
        }

        delete("/{id}") {
            val id = call.parameters["id"].toString()
            val deletedBook = dataManager.deleteBook(id)
            deletedBook?.let { it1 -> call.respond(it1) }
        }
    }
}
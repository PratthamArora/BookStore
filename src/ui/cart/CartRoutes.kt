package com.pratthamarora.ui.cart

import com.pratthamarora.ui.books.BookTemplate
import com.pratthamarora.ui.login.Session
import com.pratthamarora.utils.Endpoints
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.http.content.PartData
import io.ktor.request.receiveMultipart
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.html.i
import model.DataManagerMongoDB
import org.slf4j.LoggerFactory

fun Route.cart() {
    get(Endpoints.CART.url) {
        val session = call.sessions.get<Session>()
        call.respondHtmlTemplate(
            CartTemplate(
                session
                , DataManagerMongoDB.INSTANCE.cartForUser(session)
            )
        ) {}
    }
    post(Endpoints.DOADDTOCART.url) {
        val log = LoggerFactory.getLogger("LoginView")
        val multipart = call.receiveMultipart()
        val session = call.sessions.get<Session>()
        var bookid: String = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "bookid")
                        bookid = part.value
                }
            }
            part.dispose()
        }
        val book = DataManagerMongoDB.INSTANCE.getBookWithId(bookid)
        DataManagerMongoDB.INSTANCE.addBook(session, book)
        call.respondHtmlTemplate(BookTemplate(call.sessions.get<Session>(), DataManagerMongoDB.INSTANCE.allBooks())) {
            searchfilter {
                i {
                    +"Book added to cart"
                }
            }
        }
    }

    post(Endpoints.DOREMOVEFROMCART.url) {
        val log = LoggerFactory.getLogger("Remove from cart")
        val multipart = call.receiveMultipart()
        val session = call.sessions.get<Session>()
        var bookid: String = ""
        while (true) {
            val part = multipart.readPart() ?: break
            when (part) {
                is PartData.FormItem -> {
                    log.info("FormItem: ${part.name} = ${part.value}")
                    if (part.name == "bookid")
                        bookid = part.value
                }
            }
            part.dispose()
        }
        val book = DataManagerMongoDB.INSTANCE.getBookWithId(bookid)
        DataManagerMongoDB.INSTANCE.removeBook(session, book)
        call.respondHtmlTemplate(CartTemplate(session, DataManagerMongoDB.INSTANCE.cartForUser(session))) {
        }
    }

}
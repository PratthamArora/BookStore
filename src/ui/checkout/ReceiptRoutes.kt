package com.pratthamarora.ui.checkout

import com.pratthamarora.utils.Endpoints
import com.pratthamarora.ui.login.Session
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import model.DataManagerMongoDB

fun Route.receipt(){
    get(Endpoints.RECEIPT.url){
        val session = call.sessions.get<Session>()
        call.respondHtmlTemplate(ReceiptTemplate(session, DataManagerMongoDB.INSTANCE.cartForUser(session))){
        }
    }
}
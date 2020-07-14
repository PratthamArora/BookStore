package com.pratthamarora.ui.home

import com.pratthamarora.ui.GeneralViewTemplate
import com.pratthamarora.ui.login.Session
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.*

class HomeTemplate(val session: Session?) : Template<HTML> {
    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(session)
    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div(classes = "mt-2") {
                    h2() {
                        +"Welcome to the Bookstore"
                    }
                    p{
                        +"- We have many good deals on a lot of different topics"
                    }
                    p{
                        +"Let us know if you are looking for something that we do not currently have."
                    }
                }
            }
        }
    }
}
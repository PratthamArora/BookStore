package com.pratthamarora.ui.login

import com.pratthamarora.ui.GeneralViewTemplate
import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.*

class LoginSuccesfullTemplate(val session: Session?) : Template<HTML> {
    val greeting = Placeholder<FlowContent>()
    val basicTemplate: GeneralViewTemplate = GeneralViewTemplate(session)
    override fun HTML.apply() {
        insert(basicTemplate) {
            content {
                div(classes = "mt-2") {
                    h2() {
                        +"You have been logged in!"
                    }
                    p {
                        insert(greeting)
                    }
                }
            }
        }
    }
}
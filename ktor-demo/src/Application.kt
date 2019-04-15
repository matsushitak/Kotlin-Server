package com.example

import com.example.controller.post
import com.example.repository.PostRepository
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.Routing
import org.jetbrains.exposed.sql.Database
import java.text.DateFormat
import java.time.Duration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(DefaultHeaders)
    install(CORS) {
        maxAge = Duration.ofDays(1)
    }
    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

    val postRepository = PostRepository()

    install(Routing) {
        post(postRepository)
    }
}
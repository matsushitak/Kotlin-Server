package com.example

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    routing {
        install(ContentNegotiation) {
            gson()
        }
        get("/") {
            call.respond("Hello World!")
        }
        get("/users") {
            call.respond("Users")
        }
    }

    transaction {
        SchemaUtils.create(Users)

        Users.insert {
            it[familyName] = "山田"
            it[givenName] = "太郎"
        }

        Users.insert {
            it[familyName] = "山本"
            it[givenName] = "二郎"
        }

        Users.insert {
            it[familyName] = "田中"
            it[givenName] = "三郎"
        }

        println("Users inserted")
    }
}

object Users : Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val familyName = varchar("family_name", length = 50)
    val givenName = varchar("given_name", length = 50)
}
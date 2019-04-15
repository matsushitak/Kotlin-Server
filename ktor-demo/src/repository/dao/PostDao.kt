package com.example.repository.dao

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

object PostDao : Table("posts") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", length = 10)
    val content = varchar("content", length = 100)
    val date = datetime("date").default(DateTime(DateTimeZone.UTC))
}
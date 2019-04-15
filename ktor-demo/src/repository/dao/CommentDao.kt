package com.example.repository.dao

import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

object CommentDao : Table("comments") {
    val id = integer("id").autoIncrement()
    val postId = integer("post_id").references(PostDao.id)
    val content = varchar("content", length = 100)
    val date = datetime("date").default(DateTime(DateTimeZone.UTC))
}
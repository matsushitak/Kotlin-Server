package com.example.repository

import com.example.model.Post
import com.example.repository.dao.PostDao
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class PostRepository {

    @Throws(ExposedSQLException::class)
    fun create(post: Post) {
        transaction {
            PostDao.insertIgnore {
                it[id] = post.id
                it[title] = post.title
                it[content] = post.content
                it[date] = DateTime.now()
            }
        }
    }

    @Throws(ExposedSQLException::class)
    fun readAll(): List<Post> {
        return transaction {
            PostDao
                .selectAll()
                .map {
                    Post(
                        id = it[PostDao.id],
                        title = it[PostDao.title],
                        content = it[PostDao.content]
                    )
                }
        }
    }

    @Throws(ExposedSQLException::class)
    fun read(id: Int): Post? {
        return transaction {
            PostDao
                .select { PostDao.id eq id }
                .map {
                    Post(
                        id = it[PostDao.id],
                        title = it[PostDao.title],
                        content = it[PostDao.content]
                    )
                }
                .firstOrNull()
        }
    }

    @Throws(ExposedSQLException::class)
    fun update(id: Int, post: Post) {
        transaction {
            PostDao.update {
                it[PostDao.id] = id
                it[title] = post.title
                it[content] = post.content
                it[date] = DateTime.now()
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            PostDao.deleteIgnoreWhere {
                PostDao.id eq id
            }
        }
    }
}
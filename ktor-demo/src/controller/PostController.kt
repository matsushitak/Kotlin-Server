package com.example.controller

import com.example.model.Post
import com.example.repository.PostRepository
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import mu.KotlinLogging

fun Route.post(postRepository: PostRepository) {

    val logger = KotlinLogging.logger { }

    route("/posts") {

        get("") {
            logger.debug("Get Posts.")
            val posts = postRepository.readAll()
            call.respond(posts)
        }

        get("/{post_id}") {
            val postId = call.parameters["post_id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Parameter post id is not found.")
            logger.debug("Get Post : $postId.")
            when (val post = postRepository.read(postId)) {
                null -> call.respond(HttpStatusCode.NotFound)
                else -> call.respond(HttpStatusCode.OK, post)
            }
        }

        post("") {
            val post = call.receive(Post::class)
            logger.debug("Post Post : $post")
            try {
                postRepository.create(post)
                call.respond(HttpStatusCode.Created)
            } catch (e: Exception) {
                logger.error("Post Error : $e")
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        put("/{post_id}") {
            val postId = call.parameters["post_id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Parameter post id is not found.")
            val post = call.receive(Post::class)
            logger.debug("Put Post : $postId.")
            logger.debug("Put Post : $post")
            try {
                postRepository.update(postId, post)
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception) {
                logger.error("Put Error : $e")
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{post_id}") {
            val postId = call.parameters["post_id"]?.toIntOrNull()
                ?: throw IllegalArgumentException("Parameter post id is not found.")
            logger.debug("Delete Post : $postId.")
            try {
                postRepository.delete(postId)
                call.respond(HttpStatusCode.OK)
            } catch (e: Exception) {
                logger.error("Put Error : $e")
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}
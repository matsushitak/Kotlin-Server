package com.example.model

/**
 * コメント
 *
 * @param id ID
 * @param postId 投稿ID
 * @param content 内容
 */
data class Comment(
    val id: Int,
    val postId: String,
    val content: String
)

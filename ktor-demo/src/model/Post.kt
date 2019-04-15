package com.example.model

/**
 * 投稿
 *
 * @param id ID
 * @param title タイトル
 * @param content 内容
 */
data class Post(
    val id: Int,
    val title: String,
    val content: String
)
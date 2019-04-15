package com.example.model

/**
 * ユーザー
 *
 * @param id ID
 * @param firstName 姓
 * @param givenName 名
 */
data class User(
    val id: String,
    val firstName: String,
    val givenName: String
)
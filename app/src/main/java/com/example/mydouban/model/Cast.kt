package com.example.mydouban.model

data class Cast(
    val alt: String,
    val avatars: Images,
    val id: String,
    val name: String,
    val nameEn: String
) {
    lateinit var role: String
}
package com.bookExchange.dto

data class BookCategoryRequest(
    val name: String,
    val description: String
)

data class BookCategoryResponse(
    val status: String,
    val message: String
)

data class BookCategoriesResponse(
    val status: String,
    val message: String,
    val categories: List<BookCategory>?
)

data class BookCategory(
    val id: Long?,
    val name: String,
    val description: String
)
package com.bookExchange.dto

data class NewBook(
    val title: String,
    val description: String,
    val categoryId: Long,
    val price: Double,
    val rent: Double,
    val author: String,
    val authorMobile: String,
    val authorEmail: String,
    val coverImage: String
)

data class NewBookResponse(
    val status: String,
    val message: String
)

data class BooksByCategoryResponse(
    val status: String,
    val message: String,
    val books: List<BookByCategory>?
)

data class BookByCategory(
    val id: Long?,
    val name: String,
    val coverImage: String,
    val price: Double,
    val rentPerDay: Double
)

data class BookDetailResponse(
    val status: String,
    val message: String,
    val books: BookDetails?
)

data class BookDetails(
    val id: Long?,
    val name: String,
    val description: String,
    val author: String,
    val categoryId: Long,
    val category: String,
    val coverImage: String,
    val price: Double,
    val authorMobile: String,
    val authorEmail: String,
    val rentPerDay: Double,
)

data class BooksByFiltersResponse(
    val status: String,
    val message: String,
    val books: List<BookDetails>?
)
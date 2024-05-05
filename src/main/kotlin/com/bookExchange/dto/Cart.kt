package com.bookExchange.dto

data class CartRequest (
    val bookId: Long,
    val bookName: String,
    val purchaseType: String
)

data class CartResponse (
    val status: String,
    val message: String,
    val data: CartDetails
)

data class CartDetails (
    val totalPrice: Double,
    val books: List<BooksInCart>
)
data class BooksInCart (
    val bookId: Long,
    val bookName: String,
    val purchaseType: String,
    val price: Double
)
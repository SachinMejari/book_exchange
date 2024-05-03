package com.bookExchange.repository

import com.bookExchange.model.Books
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Books, Long> {
    @Query(
        "SELECT book FROM Books book " +
                "WHERE book.status = true and book.categoryId = :categoryId")
        fun getBooksByCategoryId(categoryId: Long): List<Books>?
    @Query(
        "SELECT book FROM Books book " +
                "WHERE book.status = true and book.id = :bookId")
    fun getBookDetailsById(bookId: Long): Books?
    @Query(
        "SELECT book FROM Books book " +
                "WHERE book.status = true and (book.title LIKE %:searchTerm% or book.author LIKE %:searchTerm% or book.category LIKE %:searchTerm%)")
    fun getBooksByFilters(searchTerm: String): List<Books>?
}
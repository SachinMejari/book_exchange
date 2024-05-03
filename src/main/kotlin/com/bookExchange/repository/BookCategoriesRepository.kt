package com.bookExchange.repository

import com.bookExchange.model.BookCategories
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookCategoriesRepository : JpaRepository<BookCategories, Long> {
    @Query(
        "SELECT category FROM BookCategories category " +
                "WHERE category.status = true"
    )
    fun getActiveCategories(): List<BookCategories>?
    @Query(
        "SELECT bk FROM BookCategories bk " +
                "WHERE bk.status = true and bk.category = :name"
    )
    fun getCategoryByName(name: String): BookCategories?

    @Query(
        "SELECT bk FROM BookCategories bk " +
                "WHERE bk.status = true and bk.id = :categoryId"
    )
    fun getCategoryById(categoryId: Long): BookCategories?
}
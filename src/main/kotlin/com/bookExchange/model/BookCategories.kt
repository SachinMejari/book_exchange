package com.bookExchange.model

import jakarta.persistence.*

@Entity
@Table(name = "book_categories")
class BookCategories (
    @Column(name = "category")
    var category: String = "",

    @Column(name = "description")
    var description: String = "",

    @Column(name = "version")
    var version: Int = 0,

    @Column(name = "status")
    var status: Boolean = true,

    @Column(name = "created_at")
    var createdAt: String = "",

    @Column(name = "updated_at")
    var updatedAt: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
package com.bookExchange.model

import jakarta.persistence.*

@Entity
@Table(name = "books")
class Books (
    @Column(name = "title")
    var title: String = "",

    @Column(name = "categoryId")
    var categoryId: Long = 0,

    @Column(name = "category")
    var category: String = "",

    @Column(name = "description")
    var description: String = "",

    @Column(name = "price")
    var price: Double = 0.0,

    @Column(name = "rent")
    var rent: Double = 0.0,

    @Column(name = "image")
    var image: String = "",

    @Column(name = "author")
    var author: String = "",

    @Column(name = "author_mobile")
    var authorMobile: String = "",

    @Column(name = "author_email")
    var authorEmail: String = "",

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
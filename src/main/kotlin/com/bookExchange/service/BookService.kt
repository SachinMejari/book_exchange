package com.bookExchange.service

import com.bookExchange.dto.*
import com.bookExchange.exception.ServiceException
import com.bookExchange.model.Books
import com.bookExchange.repository.BookCategoriesRepository
import com.bookExchange.repository.BookRepository
import com.bookExchange.utils.DateOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BookService (
    @Autowired
    private val bookRepository: BookRepository,
    @Autowired
    private val bookCategoryRepository: BookCategoriesRepository,
    @Autowired
    private val dateOperations: DateOperations
){
    fun addNewBooks(request: NewBook): NewBookResponse {
        try {
            val bookCategory = bookCategoryRepository.getCategoryById(request.categoryId)
                ?: throw ServiceException(HttpStatus.BAD_REQUEST, "Category not found")
            val newBook = Books()
            newBook.title = request.title
            newBook.categoryId = request.categoryId
            newBook.category = bookCategory.category
            newBook.author = request.author
            newBook.description = request.description
            newBook.authorMobile = request.authorMobile
            newBook.authorEmail = request.authorEmail
            newBook.image = request.coverImage
            newBook.price = request.price
            newBook.rent = request.rent
            newBook.status = true
            newBook.version = 1
            newBook.createdAt = dateOperations.getCurrentDateTime()
            newBook.updatedAt = dateOperations.getCurrentDateTime()
            bookRepository.save(newBook)

            return NewBookResponse("success", "New book added successfully")
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add new book")
        }
    }

    fun getBooksByCategoryId(categoryId:Long): BooksByCategoryResponse {
        try {
            val bookCategory = bookCategoryRepository.getCategoryById(categoryId)
                ?: throw ServiceException(HttpStatus.BAD_REQUEST, "Category not found")
            val books = bookRepository.getBooksByCategoryId(categoryId)
            if(books?.isEmpty() == true) {
                throw ServiceException(HttpStatus.NOT_FOUND, "No books found")
            }
            return BooksByCategoryResponse("success", "Books fetched successfully", books?.map {
                BookByCategory(it.id, it.title, it.image, it.price, it.rent)
            })
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get books")
        }
    }

    fun getBookDetails(bookId: Long): BookDetailResponse {
        try {
            val book = bookRepository.getBookDetailsById(bookId)
                ?: throw ServiceException(HttpStatus.BAD_REQUEST, "Book not found")
            return BookDetailResponse("success", "Book details fetched successfully", BookDetails(
                book.id, book.title, book.description, book.author, book.categoryId, book.category, book.image, book.price, book.authorMobile, book.authorEmail, book.rent
            ))
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get book details")
        }
    }

    fun getBooksbyFilters(filter: String): BooksByFiltersResponse {
        try {
            val books = bookRepository.getBooksByFilters(filter)
            if(books?.isEmpty() == true) {
                throw ServiceException(HttpStatus.NOT_FOUND, "No books found")
            }
            return BooksByFiltersResponse("success", "Books fetched successfully", books?.map {
                BookDetails(it.id, it.title, it.description, it.author, it.categoryId, it.category, it.image, it.price, it.authorMobile, it.authorEmail, it.rent)
            })
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get books")
        }
    }
}
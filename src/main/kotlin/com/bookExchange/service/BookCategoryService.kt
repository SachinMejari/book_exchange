package com.bookExchange.service

import com.bookExchange.dto.BookCategory
import com.bookExchange.dto.BookCategoryRequest
import com.bookExchange.dto.BookCategoryResponse
import com.bookExchange.exception.ServiceException
import com.bookExchange.model.BookCategories
import com.bookExchange.repository.BookCategoriesRepository
import com.bookExchange.utils.DateOperations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class BookCategoryService(
    @Autowired
    private val bookCategoryRepository: BookCategoriesRepository,
    @Autowired
    private val dateOperations: DateOperations
) {
    fun addBookCategory(request: BookCategoryRequest): BookCategoryResponse {
        try {
            // Add book category
            val bookCategory = bookCategoryRepository.getCategoryByName(request.name)
            if (bookCategory != null) {
                throw ServiceException(HttpStatus.BAD_REQUEST, "Category already exists")
            }
            var newBookCategory = BookCategories()
            newBookCategory.category = request.name
            newBookCategory.status = true
            newBookCategory.version = 1
            newBookCategory.description = request.description
            newBookCategory.createdAt = dateOperations.getCurrentDateTime()
            newBookCategory.updatedAt = dateOperations.getCurrentDateTime()
            bookCategoryRepository.save(newBookCategory)
            return BookCategoryResponse("success", "Book category added successfully")
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add book category")
        }
    }
    fun getBookCategories(): List<BookCategory> {
        try {
            // Get all book categories
            val categories =  bookCategoryRepository.getActiveCategories() ?: emptyList()
            if(categories.isEmpty()) {
                throw ServiceException(HttpStatus.NOT_FOUND, "No book categories found")
            }
            return categories.map {
                BookCategory(it.id, it.category, it.description)
            }
        } catch (e: Exception) {
            throw ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get book categories")
        }
    }
}
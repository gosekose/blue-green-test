package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.query.BookQueryDslRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.utils.findByNameThrow
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService (
    private val bookRepository: BookRepository,
    private val bookQueryDslRepository: BookQueryDslRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
) {

    fun saveBook(request: BookRequest) {
        bookRepository.save(Book(name = request.name, type = request.type))
    }

    fun loanBook(request: BookLoanRequest) {
        val book = bookRepository.findByName(request.bookName) ?: throw IllegalArgumentException()
        if (userLoanHistoryRepository.findByBookNameAndLoanStatus(request.bookName, UserLoanStatus.LOANED) != null) {
            throw IllegalArgumentException("이미 대출되어 있는 책입니다")
        }

        val user = userRepository.findByNameThrow(request.userName)
        user.loanBook(book)
    }

    fun returnBook(request: BookReturnRequest) {
        val user = userRepository.findByNameThrow(request.userName)
        user.returnBook(request.bookName)
    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Long {
        return userLoanHistoryRepository.countByLoanStatus(UserLoanStatus.LOANED)
    }

    @Transactional(readOnly = true)
    fun getBookStatistic(): List<BookStatResponse> {
        return bookQueryDslRepository.getStats()
    }
}
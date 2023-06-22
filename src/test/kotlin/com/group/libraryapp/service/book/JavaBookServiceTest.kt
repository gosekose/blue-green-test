package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import kotlin.IllegalArgumentException

@SpringBootTest
@Transactional
class JavaBookServiceTest @Autowired constructor(
    private val bookRepository: BookRepository,
    private val bookService: BookService,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    val userName = "shein"

    @BeforeEach
    fun init() {
        val requestUser = UserCreateRequest(userName, 100)
        userService.saveUser(requestUser)
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다")
    fun saveBookTest() {
        //given
        val bookName = "object"
        val request = BookRequest(bookName)

        //when
        bookService.saveBook(request)

        //then
        val result = bookRepository.findByName(bookName).orElseThrow()
        assertThat(result.name).isEqualTo(bookName)
    }

    @Test
    @DisplayName("책 대출 성공")
    fun loanBookTest() {
        //given
        val bookName = "object"
        val bookSaveRequest = BookRequest(bookName)
        val bookLoanRequest = BookLoanRequest(userName, bookName)

        //when
        bookService.saveBook(bookSaveRequest)
        bookService.loanBook(bookLoanRequest)

        //then
//        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndIsReturn(bookName, false)
//        assertThat(userLoanHistory.bookName).isEqualTo(bookName)
//        assertThat(userLoanHistory.isReturn).isFalse
    }

    @Test
    @DisplayName("책 대출을 실패한다: 이미 등록된 책")
    fun loanBook_Exception_Already_Load() {
        //given
        val bookName = "object"
        val bookSaveRequest = BookRequest(bookName)
        bookService.saveBook(bookSaveRequest)

        //when & then
        val bookLoanRequest = BookLoanRequest(userName, bookName)
        bookService.loanBook(bookLoanRequest)

        assertThrows<IllegalArgumentException> {
            bookService.loanBook(bookLoanRequest)
        }.apply {
            assertThat(message).isEqualTo("이미 대출되어 있는 책입니다")
        }
    }

    @Test
    @DisplayName("책 대출을 실패한다: 등록되지 않은 유저")
    fun loanBook_Exception_NotFound_User() {
        //given
        val bookName = "object"
        val bookSaveRequest = BookRequest(bookName)
        bookService.saveBook(bookSaveRequest)

        //when & then
        val bookLoanRequest = BookLoanRequest("other", bookName)

        assertThrows<IllegalArgumentException> {
            bookService.loanBook(bookLoanRequest)
        }
    }

    @Test
    @DisplayName("책 반납 성공")
    fun returnTest() {
        //given
        val bookName = "object"
        val bookSaveRequest = BookRequest(bookName)
        bookService.saveBook(bookSaveRequest)

        val bookLoanRequest = BookLoanRequest(userName, bookName)
        bookService.loanBook(bookLoanRequest)

        //when
        bookService.returnBook(BookReturnRequest(userName, bookName))

        //then
        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndIsReturn(bookName, true)
        assertThat(userLoanHistory.isReturn).isTrue
        assertThat(userLoanHistory.bookName).isEqualTo(bookName)
    }

    @Test
    @DisplayName("책 반납 실패: 없는 유저")
    fun return_Exception_NotFound_User() {
        //given
        val bookName = "object"
        val bookSaveRequest = BookRequest(bookName)
        bookService.saveBook(bookSaveRequest)

        val bookLoanRequest = BookLoanRequest(userName, bookName)
        bookService.loanBook(bookLoanRequest)

        //when & then
        assertThrows<IllegalArgumentException> {
            bookService.returnBook(BookReturnRequest("other", bookName))
        }
    }
}
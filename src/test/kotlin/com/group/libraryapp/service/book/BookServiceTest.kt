package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(
    private val bookRepository: BookRepository,
    private val bookService: BookService,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val userRepository: UserRepository,
) {

    val userName = "shein"

    @BeforeEach
    fun init() {
        val requestUser = UserCreateRequest(userName, 100)
        userService.saveUser(requestUser)
    }

    @AfterEach
    fun clear() {
        userLoanHistoryRepository.deleteAll()
        userRepository.deleteAll()
        bookRepository.deleteAll()
    }

    @Test
    @DisplayName("책 등록이 정상 동작한다")
    fun saveBookTest() {
        //given
        val bookName = "object"
        val type = BookType.COMPUTER
        val request = BookRequest(bookName, type)

        //when
        bookService.saveBook(request)

        //then
        val result = bookRepository.findByName(bookName)
        assertThat(result!!.name).isEqualTo(bookName)
    }

    @Test
    @DisplayName("책 대출 성공")
    fun loanBookTest() {
        //given
        val bookName = "object"
        val type = BookType.COMPUTER
        val bookSaveRequest = BookRequest(bookName, type)
        val bookLoanRequest = BookLoanRequest(userName, bookName)

        //when
        bookService.saveBook(bookSaveRequest)
        bookService.loanBook(bookLoanRequest)

        //then
        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndLoanStatus(bookName, UserLoanStatus.LOANED)
        assertThat(userLoanHistory!!.bookName).isEqualTo(bookName)
        assertThat(userLoanHistory.loanStatus).isEqualTo(UserLoanStatus.LOANED)
    }

    @Test
    @DisplayName("책 대출을 실패한다: 이미 등록된 책")
    fun loanBook_Exception_Already_Load() {
        //given
        val bookName = "object"
        val type = BookType.COMPUTER
        val bookSaveRequest = BookRequest(bookName, type)
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
        val type = BookType.COMPUTER
        val bookSaveRequest = BookRequest(bookName, type)
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
        val type = BookType.COMPUTER
        val bookSaveRequest = BookRequest(bookName, type)
        bookService.saveBook(bookSaveRequest)

        val bookLoanRequest = BookLoanRequest(userName, bookName)
        bookService.loanBook(bookLoanRequest)

        //when
        bookService.returnBook(BookReturnRequest(userName, bookName))

        //then
        val userLoanHistory = userLoanHistoryRepository.findByBookNameAndLoanStatus(bookName, UserLoanStatus.RETURNED)
        assertThat(userLoanHistory!!.loanStatus).isEqualTo(UserLoanStatus.RETURNED)
        assertThat(userLoanHistory.bookName).isEqualTo(bookName)
    }

    @Test
    @DisplayName("책 반납 실패: 없는 유저")
    fun return_Exception_NotFound_User() {
        //given
        val bookName = "object"
        val type = BookType.COMPUTER
        val bookSaveRequest = BookRequest(bookName, type)
        bookService.saveBook(bookSaveRequest)

        val bookLoanRequest = BookLoanRequest(userName, bookName)
        bookService.loanBook(bookLoanRequest)

        //when & then
        assertThrows<IllegalArgumentException> {
            bookService.returnBook(BookReturnRequest("other", bookName))
        }
    }
    
    @Test
    @DisplayName("책 대여 권수를 정상 확인한다")
    fun countLoanedBook() {
        //given
        val user = userRepository.save(User("A", null))

        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(user, "object1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(user, "object2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(user, "object3", UserLoanStatus.RETURNED),
        ))

        //when
        val count = bookService.countLoanedBook()

        //then
        assertThat(count).isEqualTo(2)
    }

    @Test
    @DisplayName("분야별 책 권수를 항상 확인한다")
    fun getBookStatisticsTest() {
        //given
        bookRepository.saveAll(listOf(
            Book.fixture("A", BookType.COMPUTER),
            Book.fixture("B", BookType.COMPUTER),
            Book.fixture("C", BookType.SCIENCE),
            Book.fixture("D", BookType.COMPUTER),
        ))

        //when
        val results = bookService.getBookStatistic()

        //then
        assertThat(results).hasSize(2)
        assertCount(results, BookType.COMPUTER, 3L)
        assertCount(results, BookType.SCIENCE, 1L)
    }

    private fun assertCount(results: List<BookStatResponse>, type: BookType, count: Long) {
        assertThat(results.first { result -> result.type == type }.count).isEqualTo(count)
    }
}
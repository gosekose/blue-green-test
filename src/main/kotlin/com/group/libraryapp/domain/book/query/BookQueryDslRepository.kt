package com.group.libraryapp.domain.book.query

import com.group.libraryapp.dto.book.response.BookStatResponse
import org.springframework.stereotype.Repository

@Repository
interface BookQueryDslRepository {

    fun getStats(): List<BookStatResponse>

}
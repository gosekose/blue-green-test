package com.group.libraryapp.domain.book

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(bookName: String): Book?
}
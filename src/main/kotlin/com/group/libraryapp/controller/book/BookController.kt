package com.group.libraryapp.controller.book

import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.common.CommonResponse
import com.group.libraryapp.service.book.BookService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController (
    private val bookService: BookService,
) {

    @PostMapping("/book")
    fun saveBook (@RequestBody request: BookRequest): ResponseEntity<CommonResponse<String>> {
        bookService.saveBook(request)
        return ResponseEntity.ok().body(CommonResponse())
    }

    @PostMapping("/book/loan")
    fun loanBook (@RequestBody request: BookLoanRequest): ResponseEntity<CommonResponse<String>> {
        bookService.loanBook(request)
        return ResponseEntity.ok().body(CommonResponse())
    }

    @PutMapping("/book/return")
    fun returnBook(@RequestBody request: BookReturnRequest): ResponseEntity<CommonResponse<String>> {
        bookService.returnBook(request)
        return ResponseEntity.ok().body(CommonResponse())
    }
}
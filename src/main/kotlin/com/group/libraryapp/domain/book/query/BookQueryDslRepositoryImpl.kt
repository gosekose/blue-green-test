package com.group.libraryapp.domain.book.query

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class BookQueryDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : BookQueryDslRepository {

    override fun getStats(): List<BookStatResponse> {
        return queryFactory
            .select(Projections.constructor(
                BookStatResponse::class.java,
                book.type,
                book.id.count()))
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}
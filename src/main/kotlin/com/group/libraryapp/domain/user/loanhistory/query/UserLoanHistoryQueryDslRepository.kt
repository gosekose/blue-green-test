package com.group.libraryapp.domain.user.loanhistory.query

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class UserLoanHistoryQueryDslRepository(
    private val queryFactory: JPAQueryFactory
) {

    fun find(bookName: String, loanStatus: UserLoanStatus? = null): UserLoanHistory? {
        return queryFactory
            .select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                loanStatus?.let { userLoanHistory.loanStatus.eq(loanStatus)}
            )
            .limit(1)
            .fetchOne()
    }

    fun count(loanStatus: UserLoanStatus): Long {
        return queryFactory
            .select(userLoanHistory.id.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.loanStatus.eq(loanStatus)
            )
            .fetchOne() ?: 0L
    }

    fun findUserHistoryContent(loanStatus: UserLoanStatus,
                               pageable: Pageable): Page<UserLoanHistory> {
        val content = queryFactory
            .select(userLoanHistory)
            .from(userLoanHistory)
            .where(userLoanHistory.loanStatus.eq(loanStatus))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val countQuery = queryFactory
            .select(userLoanHistory.count())
            .from(userLoanHistory)
            .where(userLoanHistory.loanStatus.eq(loanStatus))

        val total = countQuery.fetchOne() ?: 0

        return PageableExecutionUtils.getPage(content, pageable) { total }
    }

}
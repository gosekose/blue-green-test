package com.group.libraryapp.domain.user.query

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserQueryDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : UserQueryDslRepository {

    override fun findAllWithHistories(): List<User> {
        return queryFactory
            .select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory)
            .on(userLoanHistory.user.id.eq(user.id))
            .fetchJoin()
            .fetch()
    }
}
package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long> {

    fun findByBookNameAndLoanStatus(bookName: String, userLoanStatus: UserLoanStatus): UserLoanHistory?

    fun findAllByLoanStatus(loanStatus: UserLoanStatus): List<UserLoanHistory>

    fun countByLoanStatus(loanStatus: UserLoanStatus): Long
}
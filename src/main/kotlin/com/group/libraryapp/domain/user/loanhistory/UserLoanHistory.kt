package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

@Entity
class UserLoanHistory (
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    val bookName: String,

    @Enumerated(EnumType.STRING)
    var loanStatus: UserLoanStatus = UserLoanStatus.LOANED,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {

    companion object {
        fun fixture(
            user: User,
            bookName: String = "object",
            loanStatus: UserLoanStatus = UserLoanStatus.LOANED,
            id: Long? = null,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookName = bookName,
                loanStatus = loanStatus
            )
        }
    }

    fun doReturn() {
        this.loanStatus = UserLoanStatus.RETURNED
    }

}
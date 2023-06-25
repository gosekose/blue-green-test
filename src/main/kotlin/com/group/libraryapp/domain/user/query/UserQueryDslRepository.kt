package com.group.libraryapp.domain.user.query

import com.group.libraryapp.domain.user.User
import org.springframework.stereotype.Repository

@Repository
interface UserQueryDslRepository {

    fun findAllWithHistories(): List<User>
}
package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User
import org.jetbrains.annotations.NotNull

class UserResponse (
    @NotNull val id: Long,
    val name: String,
    val age: Int?
) {
    companion object {
        fun of(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age
            )
        }
    }
}
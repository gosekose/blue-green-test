package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.utils.fail
import com.group.libraryapp.utils.findByIdOrThrow
import com.group.libraryapp.utils.findByNameThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserService (
    private val userRepository: UserRepository,
) {

    fun saveUser(request: UserCreateRequest) {
        userRepository.save(User(name = request.name, age = request.age))
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map { user -> UserResponse(user) }
    }

    fun updateUserName(request: UserUpdateRequest) {
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    fun deleteUser(name: String) {
        val user = userRepository.findByNameThrow(name)
        userRepository.delete(user)
    }
}
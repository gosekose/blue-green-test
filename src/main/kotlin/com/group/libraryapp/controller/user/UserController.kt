package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.common.CommonResponse
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController (
    private val userService: UserService,
) {

    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest): ResponseEntity<CommonResponse<String>> {
        userService.saveUser(request)
        return ResponseEntity.ok().body(CommonResponse())
    }

    @GetMapping("/user")
    fun getUsers(): ResponseEntity<CommonResponse<List<UserResponse>>> {
        val userResponses = userService.getUsers()
        return ResponseEntity.ok().body(CommonResponse(body = userResponses))
    }

    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest): ResponseEntity<CommonResponse<String>> {
        userService.updateUserName(request)
        return ResponseEntity.ok().body(CommonResponse())
    }

    @DeleteMapping("/user")
    fun deleteUser(@RequestParam name: String): ResponseEntity<CommonResponse<String>> {
        userService.deleteUser(name)
        return ResponseEntity.ok().body(CommonResponse())
    }

    @GetMapping("/user/loan")
    fun getUserLoanHistories(): List<UserLoanHistoryResponse> {
        return userService.getUserLoanHistories()
    }

}
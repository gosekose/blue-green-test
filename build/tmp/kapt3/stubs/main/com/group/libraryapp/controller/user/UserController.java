package com.group.libraryapp.controller.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0001\u0010\t\u001a\u00020\bH\u0017J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0017J\u001a\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u00070\u0006H\u0017J\u001e\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u0017J\u001e\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\b\b\u0001\u0010\u0010\u001a\u00020\u0013H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/group/libraryapp/controller/user/UserController;", "", "userService", "Lcom/group/libraryapp/service/user/UserService;", "(Lcom/group/libraryapp/service/user/UserService;)V", "deleteUser", "Lorg/springframework/http/ResponseEntity;", "Lcom/group/libraryapp/dto/common/CommonResponse;", "", "name", "getUserLoanHistories", "", "Lcom/group/libraryapp/dto/user/response/UserLoanHistoryResponse;", "getUsers", "Lcom/group/libraryapp/dto/user/response/UserResponse;", "saveUser", "request", "Lcom/group/libraryapp/dto/user/request/UserCreateRequest;", "updateUserName", "Lcom/group/libraryapp/dto/user/request/UserUpdateRequest;", "library-app"})
@org.springframework.web.bind.annotation.RestController
public class UserController {
    private final com.group.libraryapp.service.user.UserService userService = null;
    
    public UserController(@org.jetbrains.annotations.NotNull
    com.group.libraryapp.service.user.UserService userService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.PostMapping(value = {"/user"})
    public org.springframework.http.ResponseEntity<com.group.libraryapp.dto.common.CommonResponse<java.lang.String>> saveUser(@org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.RequestBody
    com.group.libraryapp.dto.user.request.UserCreateRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.GetMapping(value = {"/user"})
    public org.springframework.http.ResponseEntity<com.group.libraryapp.dto.common.CommonResponse<java.util.List<com.group.libraryapp.dto.user.response.UserResponse>>> getUsers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.PutMapping(value = {"/user"})
    public org.springframework.http.ResponseEntity<com.group.libraryapp.dto.common.CommonResponse<java.lang.String>> updateUserName(@org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.RequestBody
    com.group.libraryapp.dto.user.request.UserUpdateRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.DeleteMapping(value = {"/user"})
    public org.springframework.http.ResponseEntity<com.group.libraryapp.dto.common.CommonResponse<java.lang.String>> deleteUser(@org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.RequestParam
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @org.springframework.web.bind.annotation.GetMapping(value = {"/user/loan"})
    public java.util.List<com.group.libraryapp.dto.user.response.UserLoanHistoryResponse> getUserLoanHistories() {
        return null;
    }
}
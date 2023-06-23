package com.group.libraryapp.dto.common

data class CommonResponse<T> (
    val code: Int = 200,
    val status: String = "OK",
    val body: T? = null,
)
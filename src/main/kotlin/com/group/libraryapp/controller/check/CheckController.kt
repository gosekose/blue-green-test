package com.group.libraryapp.controller.check

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckController (
    private val env: Environment,
) {

    @GetMapping("/profile")
    fun getProfile(): String {
        return env.activeProfiles.firstOrNull() ?: ""
    }
}
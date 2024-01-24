package com.facebook.users.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class UserController {

    @GetMapping("users")
    fun getUser(): String {
        return "string"
    }
}
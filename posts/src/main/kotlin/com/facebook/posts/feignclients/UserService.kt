package com.facebook.posts.feignclients

import com.facebook.posts.payload.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*


@Service
@FeignClient(value = "users", url = "http://localhost:8222")
interface UserService {
    @GetMapping("/api/v1/user/{id}")
    fun getOne(@PathVariable id: UUID) : User
}
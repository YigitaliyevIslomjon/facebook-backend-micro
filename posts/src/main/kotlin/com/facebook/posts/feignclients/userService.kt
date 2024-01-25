package com.facebook.posts.feignclients

import com.facebook.posts.payload.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

@FeignClient(value = "USER-SERVICE", url = "http://localhost:8090")
interface UserService {
    @GetMapping(value = ["/api/user/{id}"])
    fun getOne(@PathVariable id : UUID) : User
}
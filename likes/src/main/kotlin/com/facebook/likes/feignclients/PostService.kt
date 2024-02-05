package com.facebook.likes.feignclients

import com.facebook.likes.payload.Post
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.*


@Service
@FeignClient(value = "posts-service", url = "http://localhost:8222")
interface PostService {
    @GetMapping("/api/v1/post/{id}")
    fun getOne(@PathVariable id: UUID) : Post
}